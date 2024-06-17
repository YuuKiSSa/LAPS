package sg.edu.nus.spring_laps.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.StaffService;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;


@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/staffDashboard")
    public String staffDashboard(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        Staff staff = staffService.findByUserId(userId);
        model.addAttribute("staff", staff);
        List<ApplicationType> applicationTypes = applicationService.findAllApplicationTypes();
        model.addAttribute("applicationTypes", applicationTypes);
        return "staff-dashboard";
    }
}
