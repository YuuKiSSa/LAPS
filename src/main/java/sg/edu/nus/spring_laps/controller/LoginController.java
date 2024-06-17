package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.StaffService;


@Controller
public class LoginController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,@RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("role") String role, Model model) {
    	HttpSession session = request.getSession();
    	if (role.equals("Admin")) {
            Admin admin = staffService.authenticateAdmin(userId, password);
            if (admin != null) {
                model.addAttribute("user", admin);
                session.setAttribute("userId",admin.getUserId());
                return "adminHome";
            }
        } else {
            Staff staff = staffService.authenticateStaff(userId, password);
            if (staff != null) {
                model.addAttribute("user", staff);
                session.setAttribute("userId",staff.getUserId());
                if (staff.getHierarchy() >= 1) {
                    return "managerHome"; 
                } else {
                    return "employeeHome"; 
                }
            }
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}
