package sg.edu.nus.spring_laps.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.spring_laps.model.*;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.EmailService;
import sg.edu.nus.spring_laps.service.PublicHolidayService;
import sg.edu.nus.spring_laps.service.StaffService;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/staffDashboard")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    StaffService staffService;
    @Autowired
    PublicHolidayService publicHolidayService;

    @Autowired
    EmailService emailService;
    
    @GetMapping
    public String staffDashboard(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        Staff staff = staffService.findByUserId(userId);
        model.addAttribute("staff", staff);
        List<ApplicationType> applicationTypes = applicationService.findAllApplicationTypes();
        model.addAttribute("applicationTypes", applicationTypes);
        return "staff/staff-dashboard";
    }

    @GetMapping("/createApplication")
    public String createApplication(@RequestParam(value = "type", required = false) int type, Model model, HttpSession session) {
        ApplicationForm applicationForm = new ApplicationForm();
        if (session.getAttribute("userId") == null){
            return "redirect:/login";
        }else{
            applicationForm.setUserId(session.getAttribute("userId").toString());
        }
        Staff staff = staffService.findByUserId(session.getAttribute("userId").toString());
        if (!staff.getStatus()){
            return "staff/not-allow-apply";
        }
        ApplicationType applicationType = applicationService.findApplicationTypeById(type);
        model.addAttribute("applicationType", applicationType);
        String appTypeName = applicationType.getType();
        applicationForm.setApplicationType(appTypeName);

        model.addAttribute("applicationForm", applicationForm);

        if (appTypeName.equals("Compensation Leave")){
            long time = compensationTime(staff);
            model.addAttribute("compensationHours", time);
            model.addAttribute("compensationTimes", time/4);
        } else if (appTypeName.equals("Annual Leave")) {
            int annualLeaveDays = staff.getEntitle();
            model.addAttribute("annualLeaveDays", annualLeaveDays);
            int annualLeaveDaysLeft = calAnnualLeaveDaysLeft(annualLeaveDays, staff);
            model.addAttribute("annualLeaveDaysLeft", annualLeaveDaysLeft);
        }
        switch (appTypeName){
            case "Annual Leave": return "staff/annual-leave";
            case "Medical Leave": return  "staff/medical-leave";
            case "Compensation Leave": return  "staff/compensation-leave";
            case "Compensation": return  "staff/compensation";
            default: return "staff/create-application";
        }
    }

    @PostMapping("/createApplication")
    public String createApplication(@Valid @ModelAttribute("applicationForm") ApplicationForm applicationForm,
                                    BindingResult bindingResult, Model model,
                                    HttpSession session) {
        Staff staff = staffService.findByUserId(applicationForm.getUserId());
        String appTypeName = applicationForm.getApplicationType();

        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("globalErrors", bindingResult.getGlobalErrors());

            if (appTypeName.equals("Annual Leave")) {
                int annualLeaveDays = staff.getEntitle();
                model.addAttribute("annualLeaveDays", annualLeaveDays);
                int annualLeaveDaysLeft = calAnnualLeaveDaysLeft(annualLeaveDays, staff);
                model.addAttribute("annualLeaveDaysLeft", annualLeaveDaysLeft);
                return "staff/annual-leave";
            } else if (appTypeName.equals("Compensation Leave")) {
                long time = compensationTime(staff);
                model.addAttribute("compensationHours", time);
                model.addAttribute("compensationTimes", time / 4);
                return "staff/compensation-leave";
            } else if (appTypeName.equals("Medical Leave")) {
                return "staff/medical-leave";
            } else if (appTypeName.equals("Compensation")) {
                return "staff/compensation";
            } else {
                return "staff/create-application";
            }
        }

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
        if (!type.equals("Compensation")) {
            if (selectTime != null) {
                if (selectTime.equals("Morning")) {
                    savedApplication.setStartTime(startTime.plusHours(9));
                    savedApplication.setEndTime(endTime.plusHours(13));
                } else {
                    savedApplication.setStartTime(startTime.plusHours(13));
                    savedApplication.setEndTime(endTime.plusHours(17));
                }
            } else {
                savedApplication.setStartTime(startTime.plusHours(9));
                savedApplication.setEndTime(endTime.plusHours(17));
            }
        } else {
            savedApplication.setStartTime(startTime);
            savedApplication.setEndTime(endTime);
        }
        savedApplication.setDescription(reason);
        Application newApplication = applicationService.saveApplication(savedApplication);
        // 发送邮件通知给上级管理者
        List<Staff> higherManagers = staffService.findHigherManagers(staff);
        String emailContent = "A new leave application has been submitted by " + staff.getName() + ". Please review it at your earliest convenience.";
        for (Staff manager : higherManagers) {
            emailService.sendSimpleMessage(manager.getEmail(), "New Leave Application", emailContent);
        }
        session.setAttribute("applicationId", newApplication.getId());
        return "redirect:/staffDashboard/displayApplication";
    }

    @GetMapping("/displayApplication")
    public String displayApplication(@RequestParam(value = "applicationId", required = false) Long applicationId, HttpSession session, Model model) {
        if (session.getAttribute("applicationId") == null && applicationId == null) {
            return "staff/application-error";
        }
        if (applicationId == null) {
            applicationId = (Long) session.getAttribute("applicationId");
        }
        Application application = applicationService.findApplicationById(applicationId);
        if (application == null) {
            return "staff/application-error";
        }
        model.addAttribute("displayApplication", application);
        return "staff/display-application";
    }

    @GetMapping("/editApplication")
    public String editApplication(@RequestParam(value = "applicationId") Long applicationId, HttpSession session, Model model) {
        Application application = applicationService.findApplicationById(applicationId);
        if (application == null) {
            return "staff/application-error";
        }
        model.addAttribute("applicationId", applicationId);
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "/login";
        }
        Staff staff = staffService.findByUserId(userId);

        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setApplicationId(applicationId);
        applicationForm.setUserId(session.getAttribute("userId").toString());
        String appTypeName = application.getApplicationType().getType();
        applicationForm.setApplicationType(appTypeName);
        applicationForm.setStartTime(application.getStartTime());
        applicationForm.setEndTime(application.getEndTime());
        model.addAttribute("applicationForm", applicationForm);

        if (appTypeName.equals("Compensation Leave")){
            long time = compensationTime(staff);
            model.addAttribute("compensationHours", time);
            model.addAttribute("compensationTimes", time/4);
        } else if (appTypeName.equals("Annual Leave")) {
            int annualLeaveDays = staff.getDepartment().getAnnualLeave();
            model.addAttribute("annualLeaveDays", annualLeaveDays);
            int annualLeaveDaysLeft = calAnnualLeaveDaysLeft(annualLeaveDays, staff);
            model.addAttribute("annualLeaveDaysLeft", annualLeaveDaysLeft);
        }

        switch (application.getApplicationType().getType()){
            case "Annual Leave": return "staff/annual-leave";
            case "Medical Leave": return  "staff/medical-leave";
            case "Compensation Leave": return  "staff/compensation-leave";
            case "Compensation": return  "staff/compensation";
            default: return "staff/create-application";
        }
    }

    @PostMapping("/editApplication")
    public String editApplication(@Valid @ModelAttribute("applicationForm") ApplicationForm applicationForm,
                                  BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("globalErrors", bindingResult.getGlobalErrors());
            switch (applicationForm.getApplicationType()) {
                case "Annual Leave":
                    return "staff/annual-leave";
                case "Medical Leave":
                    return "staff/medical-leave";
                case "Compensation Leave":
                    return "staff/compensation-leave";
                case "Compensation":
                    return "staff/compensation";
                default:
                    return "staff/create-application";
            }
        }

        Long applicationId = applicationForm.getApplicationId();
        Application savedApplication = applicationService.findApplicationById(applicationId);
        if (savedApplication == null) {
            return "staff/application-error";
        }
        savedApplication.setStatus("Updated");
        String selectTime = applicationForm.getSelectTime();
        LocalDateTime startTime = applicationForm.getStartTime();
        LocalDateTime endTime = applicationForm.getEndTime();
        if(!applicationForm.getApplicationType().equals("Compensation")){
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
        savedApplication.setDescription(applicationForm.getReason());
        Application newApplication = applicationService.saveApplication(savedApplication);
        session.setAttribute("applicationId", newApplication.getId());
        return "redirect:/staffDashboard/displayApplication";
    }

    @GetMapping("/history")
    public String history(@RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "/login";
        }
        Staff staff = staffService.findByUserId(userId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> applications = applicationService.findAllByStaff(staff, pageable);
        model.addAttribute("applications", applications.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", applications.getTotalPages());
        return "staff/application-history";
    }

    @GetMapping("/cancelApplication")
    public String cancelApplication(@RequestParam("applicationId") Long applicationId, HttpSession session, Model model) {
        if (applicationId == null) {
            return "staff/application-error";
        }
        if (session.getAttribute("userId") == null) {
            return "/login";
        }
        Application application = applicationService.findApplicationById(applicationId);
        if (application == null) {
            return "staff/application-error";
        }
        Staff staff = application.getStaff();
        String status = application.getStatus();
        boolean isCancelled = false;
        if (status.equals("Approved")) {
            application.setStatus("Cancel");
            isCancelled = true;
            
        }else{
            application.setStatus("Deleted");
        }
        applicationService.saveApplication(application);
        
     // Send email notification to higher managers
        List<Staff> higherManagers = staffService.findHigherManagers(staff);
        String emailContent = "The leave application submitted by " + staff.getName();
        emailContent += isCancelled ? " has been cancelled." : " has been deleted.";
        for (Staff manager : higherManagers) {
            emailService.sendSimpleMessage(manager.getEmail(), "Leave Application Notification", emailContent);
        }
        
        session.setAttribute("applicationId", applicationId);
        return "redirect:/staffDashboard/displayApplication";
    }

    private long compensationTime(Staff staff){
        ApplicationType applicationTypePlus = applicationService.findApplicationTypeByName("Compensation");
        ApplicationType applicationTypeSub = applicationService.findApplicationTypeByName("Compensation Leave");
        List<Application> applicationsPlus = applicationService.findApplicationsByStaffAndApplicationType(staff, applicationTypePlus);
        List<Application> applicationsSub = applicationService.findApplicationsByStaffAndApplicationType(staff, applicationTypeSub);
        Duration totalDuration = Duration.ZERO;
        for (Application application : applicationsPlus) {
            if (application.getStatus().equals("Approved")){
                totalDuration = totalDuration.plus(Duration.between(application.getStartTime(), application.getEndTime()));
            }
        }
        for (Application application : applicationsSub) {
            if (application.getStatus().equals("Cancel") || application.getStatus().equals("Deleted") || application.getStatus().equals("Rejected")){
                continue;
            }
            totalDuration = totalDuration.minus(Duration.between(application.getStartTime(), application.getEndTime()));
        }
        return totalDuration.toHours();
    }

    private boolean isHoliday(LocalDate date){
        List<LocalDate> publicHolidaysDates = publicHolidayService.findAllPublicHolidaysDate();
        if (publicHolidaysDates.contains(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return true;
        }
        return false;
    }

    private int calAnnualLeaveDaysLeft(int annualLeaveDays, Staff staff){
        List<Application> applications = applicationService.findAnnualLeaveByStaffAndYear(staff, LocalDateTime.now().getYear());
        if (annualLeaveDays > 14){
            for (Application application : applications) {
                int days = application.getEndTime().getDayOfYear() - application.getStartTime().getDayOfYear() + 1;
                annualLeaveDays-=days;
            }
        }else{
            for (Application application : applications) {
                int days = application.getEndTime().getDayOfYear() - application.getStartTime().getDayOfYear() + 1;
                for (int i = 0; i < days; i++){
                    LocalDate date = application.getStartTime().toLocalDate().plusDays(i);
                    if (!isHoliday(date)){
                        annualLeaveDays--;
                    }
                }
            }
        }
        return annualLeaveDays;
    }
}
