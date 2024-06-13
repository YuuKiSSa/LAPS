package sg.edu.nus.spring_laps.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sg.edu.nus.spring_laps.interfacemethods.StaffInterface;
import sg.edu.nus.spring_laps.model.Staff;


@Controller
public class StaffController {
    @Autowired
    private StaffInterface staffInterface;

    @GetMapping("/staffDashboard")
    public String staffDashboard(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        Staff staff = staffInterface.findStaffById("111111");
        if (staff == null) {
            return "redirect:/login";
        }
        model.addAttribute("staff", staff);
        return "staff-dashboard";
    }
}
