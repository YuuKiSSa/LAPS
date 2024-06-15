package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.StaffService;

@Controller
public class LoginController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String userId, @RequestParam String password, Model model) {
        Staff user = staffService.findByUserId(userId);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/home/" + userId;
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
