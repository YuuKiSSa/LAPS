package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.StaffRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/{userId}")
    public String home(@PathVariable String userId, Model model) {
        Staff user = staffRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        model.addAttribute("user", user);

        if (user.getHierarchy() == 0) {
            return "employeeHome";
        } else if (user.getHierarchy() >= 1 && user.getHierarchy() <= 3) {
            return "managerHome";
        } else {
            return "adminHome";
        }
    }
}
