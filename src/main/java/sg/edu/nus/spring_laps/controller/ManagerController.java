package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import sg.edu.nus.spring_laps.service.StaffService;
import sg.edu.nus.spring_laps.repository.StaffRepository;

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
    private StaffRepository staffRepository;
    @Autowired
    private StaffService staffService;
    @GetMapping("/applications/{userId}")
    public String viewApplications(@PathVariable String userId, Model model) {
        Staff manager = staffRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        List<Application> applications = applicationService.getApplicationsForManager(manager.getHierarchy(), manager.getDepartment().getId());
        Map<Staff, List<Application>> applicationsByStaff = applications.stream().collect(Collectors.groupingBy(Application::getStaff));
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
        List<Staff> subordinates = staffService.getSubordinates(manager.getHierarchy());
        List<Application> applications = applicationService.getApplicationsForSubordinates(subordinates);
        model.addAttribute("applications", applications);
        model.addAttribute("userId", userId);
        return "manager/subordinatesHistory";
    }
    
    @PostMapping("/applications/{id}/approve")
    public ResponseEntity<?> approveApplication(@PathVariable Long id, @RequestParam String userId) {
        applicationService.approveApplication(id);
        //return "redirect:/manager/applications/" + userId;
        return ResponseEntity.ok("success");
    }

    @PostMapping("/applications/{id}/reject")
    public ResponseEntity<?> rejectApplication(@PathVariable Long id, @RequestParam String comment, @RequestParam String userId) {
        applicationService.rejectApplication(id, comment);
        //return "redirect:/manager/applications/" + userId;
        return ResponseEntity.ok("success");
    }
}
