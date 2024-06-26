package sg.edu.nus.spring_laps.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.StaffRepository;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.ReportService;
import sg.edu.nus.spring_laps.service.StaffService;
import sg.edu.nus.spring_laps.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private StaffService staffService;

	@GetMapping("/home/{userId}")
	public String Home(@PathVariable String userId, Model model) {
		Staff manager = staffRepository.findByUserId(userId);
		model.addAttribute("user", manager);
		return "manager/manager-home";
	}

	@GetMapping("/applications/{userId}")
	public String viewApplications(@PathVariable String userId, Model model) {
		Staff manager = staffRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
		List<Application> applications = applicationService.getApplicationsForManager(manager.getHierarchy(),
				manager.getDepartment().getId());
		List<Application> filteredApplications = applications.stream().filter(
				application -> "Applied".equals(application.getStatus()) || "Updated".equals(application.getStatus()))
				.toList();
		Map<Staff, List<Application>> applicationsByStaff = filteredApplications.stream()
				.collect(Collectors.groupingBy(Application::getStaff));
		model.addAttribute("applicationsByStaff", applicationsByStaff);
		model.addAttribute("userId", userId); // 传递 userId 以便在重定向时使用
		return "manager/manager-applications";
	}

	@GetMapping("/application/{userId}/{applicationId}/details")
	public String viewApplicationDetails(@PathVariable String userId, @PathVariable Long applicationId, Model model) {
		Application application = applicationService.findApplicationById(applicationId);
		model.addAttribute("app", application);
		model.addAttribute("userId", userId);
		return "manager/application-details";
	}


    @GetMapping("/subordinates/{userId}/history")
    public String viewSubordinatesHistory(@PathVariable String userId, Model model,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Staff manager = staffRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        List<Staff> subordinates = staffService.getSubordinates(manager.getHierarchy(),manager.getDepartment().getId());
        Page<Application> applications = applicationService.getApplicationsForSubordinates(subordinates,page,size);
        model.addAttribute("applications", applications);
        model.addAttribute("userId", userId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", applications.getTotalPages());
        model.addAttribute("pageSize", size);
        return "manager/subordinates-history";
    }
    


    @PostMapping("/applications/{id}/approve")
    public String approveApplication(@PathVariable Long id, @RequestParam String userId) {
        applicationService.approveApplication(id);
        Application application = applicationService.findApplicationById(id);

        String loginLink = "http://localhost:8080/login"; // 替换为你的实际登录页面URL
        String emailContent = "Your leave application has been approved. You can view the details and comments by logging in here: " + loginLink;
        emailService.sendSimpleMessage(application.getStaff().getEmail(), "Leave Application Approved", emailContent);
        return "redirect:/manager/applications/" + userId;
    }


	@PostMapping("/applications/{id}/reject")
	public String rejectApplication(@PathVariable Long id, @RequestParam String comment, @RequestParam String userId) {
		applicationService.rejectApplication(id, comment);
		Application application = applicationService.findApplicationById(id);
		String loginLink = "http://localhost:8080/login";
		String emailContent = "Your leave application has been rejected. Comment: " + comment
				+ ". You can view the details and comments by logging in here: " + loginLink;
		emailService.sendSimpleMessage(application.getStaff().getEmail(), "Leave Application Rejected", emailContent);
		return "redirect:/manager/applications/" + userId;
	}

	// report .csv views
	@GetMapping("/report/leave")
	public ResponseEntity<byte[]> getLeaveReport(@RequestParam LocalDateTime startTime,
			@RequestParam LocalDateTime endTime, @RequestParam String applicationType) {
		List<Application> applications = reportService.getApplicationsByPeriodAndType(startTime, endTime,
				applicationType);
		String csvContent = generateCSV(applications);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=leave_report.csv")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(csvContent.getBytes(StandardCharsets.UTF_8));
	}

	@GetMapping("/report/compensation")
	public ResponseEntity<byte[]> getCompensationReport(@RequestParam(required = false) String userId) {
		List<Application> applications = (!userId.isEmpty()) ? reportService.getCompensationClaimsByStaffUserId(userId)
				: reportService.getAllCompensationClaims();
		String csvContent = generateCSV(applications);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=compensation_report.csv")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(csvContent.getBytes(StandardCharsets.UTF_8));
	}

	private String generateCSV(List<Application> applications) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
			writer.write("ID,userID,applicationType,Start Time,End Time,Status\n");
			for (Application application : applications) {
				String name = (application.getStaff() != null) ? application.getStaff().getName() : "N/A";
				String type = application.getApplicationType().getType();
				writer.write(String.format("%d,%s,%s,%s,%s,%s\n", application.getId(), name, type,
						application.getStartTime().toString(), application.getEndTime().toString(),
						application.getStatus()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toString();
	}
}
