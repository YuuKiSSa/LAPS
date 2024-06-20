package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.EmailService;
import sg.edu.nus.spring_laps.service.ReportService;
import sg.edu.nus.spring_laps.service.StaffService;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;
import sg.edu.nus.spring_laps.repository.StaffRepository;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    @GetMapping("/applications/{userId}")
    public String viewApplications(@PathVariable String userId, Model model) {
        Staff manager = staffRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        List<Application> applications = applicationService.getApplicationsForManager(manager.getHierarchy(), manager.getDepartment().getId());
        List<Application> filteredApplications = applications.stream()
        	    .filter(application -> "Applied".equals(application.getStatus())|| "Updated".equals(application.getStatus()))
        	    .collect(Collectors.toList());
        Map<Staff, List<Application>> applicationsByStaff = filteredApplications.stream().collect(Collectors.groupingBy(Application::getStaff));
        model.addAttribute("applicationsByStaff", applicationsByStaff);
        model.addAttribute("userId", userId);  // 传递 userId 以便在重定向时使用
        return "manager/applications";
    }

    /*@GetMapping("/application/{userId}/{applicationId}/details")
    public String viewApplicationDetails(@PathVariable String userId, @PathVariable Long applicationId, Model model) {
        Optional<Application> applicationOpt = applicationService.findById(applicationId);
        if (applicationOpt.isPresent()) {
            Application application = applicationOpt.get();
            if (application.getStaff() != null) {
                model.addAttribute("application", application);
                model.addAttribute("userId", userId);
                return "manager/applicationDetails";
            } else {
                // Handle case where staff is null
                model.addAttribute("error", "Staff information is missing for this application.");
                return "error/500";
            }
        } else {
            // Handle case where application is not found
            return "error/404";
        }*/
    
    @GetMapping("/application/{userId}/{applicationId}/details")
    public String viewApplicationDetails(@PathVariable String userId, @PathVariable Long applicationId, Model model) {
        Optional<Application> applicationOpt = applicationService.findById(applicationId);
        model.addAttribute("applicationOpt", applicationOpt);
        model.addAttribute("userId", userId);
        return "manager/applicationDetails";
        }

    @GetMapping("/subordinates/{userId}/history")
    public String viewSubordinatesHistory(@PathVariable String userId, Model model) {
        Staff manager = staffRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        List<Staff> subordinates = staffService.getSubordinates(manager.getHierarchy(),manager.getDepartment().getId());
        List<Application> applications = applicationService.getApplicationsForSubordinates(subordinates);
        model.addAttribute("applications", applications);
        model.addAttribute("userId", userId);
        return "manager/subordinatesHistory";
    }
    
   
    @PostMapping("/applications/{id}/approve")
    public String approveApplication(@PathVariable Long id, @RequestParam String userId,Model model) {
        applicationService.approveApplication(id);
        Application application = applicationService.findById(id).orElseThrow();
        String loginLink = "http://localhost:8080/login";  
        String emailContent = "Your leave application has been approved. You can view the details and comments by logging in here: " + loginLink;
        emailService.sendSimpleMessage(application.getStaff().getEmail(), "Leave Application Approved", emailContent);
        return "redirect:/manager/applications/" + userId;
    }

    @PostMapping("/applications/{id}/reject")
    public String rejectApplication(@PathVariable Long id, @RequestParam String comment, @RequestParam String userId,Model model) {
        applicationService.rejectApplication(id, comment);   
        Application application = applicationService.findById(id).orElseThrow();
        String loginLink = "http://localhost:8080/login";  
        String emailContent = "Your leave application has been rejected. Comment: " + comment + ". You can view the details and comments by logging in here: " + loginLink;
        emailService.sendSimpleMessage(application.getStaff().getEmail(), "Leave Application Rejected", emailContent);
        return "redirect:/manager/applications/" + userId;
    }

    
    //report .csv views
    @GetMapping("/report/leave")
    public ResponseEntity<byte[]> getLeaveReport(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam String applicationType) {
        List<Application> applications = reportService.getApplicationsByPeriodAndType(startTime, endTime,applicationType);
        String csvContent = generateCSV(applications);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=leave_report.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(csvContent.getBytes(StandardCharsets.UTF_8));
    }

    @GetMapping("/report/compensation")
    public ResponseEntity<byte[]> getCompensationReport(@RequestParam(required = false) String userId) {
        List<Application> applications = (userId != null)
                ? reportService.getCompensationClaimsByStaffUserId(userId)
                : reportService.getAllCompensationClaims();
        String csvContent = generateCSV(applications);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=compensation_report.csv")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(csvContent.getBytes(StandardCharsets.UTF_8));
    }

    public String generateCSV(List<Application> applications) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            writer.write("ID,userID,applicationType,Start Time,End Time,Status\n");
            for (Application application : applications) {
            	 String userId = (application.getStaff() != null) ? application.getStaff().getUserId().toString() : "N/A";
            	 String type = application.getApplicationType().getType();
                writer.write(String.format("%d,%s,%s,%s,%s,%s\n",
                        application.getId(),
                        userId,
                        type,
                        application.getStartTime().toString(),
                        application.getEndTime().toString(),
                        application.getStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
    
}
