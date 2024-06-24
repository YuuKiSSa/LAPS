package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.service.ApplicationService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/movement")
public class MovementRegisterController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/register")
    public String showMovementRegister(@RequestParam(name = "month", required = false)
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate month,
                                       Model model) {
        if (month == null) {
            month = LocalDate.now();
        }

        // Get the current month and year
        YearMonth yearMonth = YearMonth.from(month);

        // Fetch applications (leave details) for the current month
        List<Application> applications = applicationService.findApplicationsByMonth(yearMonth);

        // Prepare previous and next month navigation
        LocalDate previousMonth = yearMonth.minusMonths(1).atDay(1);
        LocalDate nextMonth = yearMonth.plusMonths(1).atDay(1);

        // Format the month for display
        String formattedMonth = yearMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH));

        model.addAttribute("applications", applications);
        model.addAttribute("currentMonth", formattedMonth);
        model.addAttribute("previousMonth", previousMonth);
        model.addAttribute("nextMonth", nextMonth);

        return "movementRegister";
    }
}
