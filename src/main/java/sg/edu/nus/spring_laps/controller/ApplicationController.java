package sg.edu.nus.spring_laps.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationForm;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.StaffService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/staffDashboard")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    StaffService staffService;

    @GetMapping("/createApplication")
    public String createApplication(@RequestParam(value = "type", required = false) int type, Model model, HttpSession session) {
        ApplicationForm applicationForm = new ApplicationForm();
        if (session.getAttribute("userId") == null){
            return "redirect:/login";
        }else{
            applicationForm.setUserId(session.getAttribute("userId").toString());
        }
        ApplicationType applicationType = applicationService.findApplicationTypeById(type);
        model.addAttribute("applicationType", applicationType);
        String appTypeName = applicationType.getType();
        applicationForm.setApplicationType(appTypeName);

        model.addAttribute("applicationForm", applicationForm);
        Staff staff = staffService.findByUserId(session.getAttribute("userId").toString());
        model.addAttribute("annualLeaveDays", staff.getDepartment().getAnnualLeave());

        if (appTypeName.equals("Compensation Leave")){
            long time = compensationTime(staff);
            model.addAttribute("compensationHours", time);
            model.addAttribute("compensationTimes", time/4);
        }
        switch (appTypeName){
            case "Annual Leave": return "annual-leave";
            case "Medical Leave": return  "medical-leave";
            case "Compensation Leave": return  "compensation-leave";
            case "Compensation": return  "compensation";
            default: return "create-application";
        }
    }

    @PostMapping("/createApplication")
    public String createApplication(@Valid @ModelAttribute("applicationForm") ApplicationForm applicationForm,
                                    BindingResult bindingResult,Model model,
                                    HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("applicationTypes", applicationService.findAllApplicationTypes());
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("globalErrors", bindingResult.getGlobalErrors());
            switch (applicationForm.getApplicationType()) {
                case "Annual Leave":
                    return "annual-leave";
                case "Medical Leave":
                    return "medical-leave";
                case "Compensation Leave":
                    return "compensation-leave";
                case "Compensation":
                    return "compensation";
                default:
                    return "create-application";
            }
        }
        Staff staff = staffService.findByUserId(applicationForm.getUserId());

        String type = applicationForm.getApplicationType();
        LocalDateTime startTime = applicationForm.getStartTime();
        String selectTime = applicationForm.getSelectTime();
        LocalDateTime endTime = applicationForm.getEndTime();
        String reason = applicationForm.getReason();

        Application savedApplication = new Application();
        savedApplication.setStaff(staff);
        savedApplication.setStatus("Applied");
        ApplicationType applicationType = applicationService.findApplicationTypeByName(type);
        savedApplication.setApplicationType(applicationType);
        if(!type.equals("Compensation")){
            if(selectTime != null){
                if (selectTime.equals("Morning")) {
                    savedApplication.setStartTime(startTime.plusHours(9));
                    savedApplication.setEndTime(endTime.plusHours(13));
                }else{
                    savedApplication.setStartTime(startTime.plusHours(13));
                    savedApplication.setEndTime(endTime.plusHours(17));
                }
        }else{
                savedApplication.setStartTime(startTime.plusHours(9));
                savedApplication.setEndTime(endTime.plusHours(17));
             }
        }else{
            savedApplication.setStartTime(startTime);
            savedApplication.setEndTime(endTime);
        }
        savedApplication.setDescription(reason);
        Application newApplication = applicationService.saveApplication(savedApplication);
        session.setAttribute("applicationId", newApplication.getId());
        return "redirect:/staffDashboard/displayApplication";
    }

    @GetMapping("/displayApplication")
    public String displayApplication(HttpSession session, Model model) {
        if (session.getAttribute("applicationId") == null) {
            return "application-error";
        }
        Long applicationId = (Long) session.getAttribute("applicationId");
        Application application = applicationService.findApplicationById(applicationId);
        if (application == null) {
            return "application-error";
        }
        model.addAttribute("displayApplication", application);
        return "display-application";
    }


    public long compensationTime(Staff staff){
        ApplicationType applicationTypePlus = applicationService.findApplicationTypeByName("Compensation");
        ApplicationType applicationTypeSub = applicationService.findApplicationTypeByName("Compensation Leave");
        List<Application> applicationsPlus = applicationService.findApplicationsByStaffAndApplicationType(staff, applicationTypePlus);
        List<Application> applicationsSub = applicationService.findApplicationsByStaffAndApplicationType(staff, applicationTypeSub);
        Duration totalDuration = Duration.ZERO;
        for (Application application : applicationsPlus) {
            totalDuration = totalDuration.plus(Duration.between(application.getStartTime(), application.getEndTime()));
        }
        for (Application application : applicationsSub) {
            totalDuration = totalDuration.minus(Duration.between(application.getStartTime(), application.getEndTime()));
        }
        return totalDuration.toHours();
    }
}
