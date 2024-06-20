package sg.edu.nus.spring_laps.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.AdminService;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.ApplicationTypeService;

@Controller
@RequestMapping("/admin")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ApplicationTypeService applicationTypeService;
	
	@GetMapping("/appSearch")
	public String searchApplication(ModelMap model) {
		return "appResearch";
	}
	@PostMapping("/appSearch")
	public String searchApp(@RequestParam(value = "query", required = false) String query, ModelMap model) {
	    Set<Application> resultSet = new HashSet<>();   
	    if (query != null && !query.isEmpty()) {
	        Application applicationById = applicationService.findApplicationById(query);
	        List<Application> staffById = applicationService.findApplicationByUserId(query);
	        List<Application> staffByName = applicationService.findApplicationByName(query);	
	        if (applicationById != null) {
	            resultSet.add(applicationById);
	        }
	        if (staffById != null) {
	            resultSet.addAll(staffById);
	        }
	        if (staffByName != null) {
	            resultSet.addAll(staffByName);
	        }

	        model.addAttribute("applications", resultSet);
	    } else {
	        model.addAttribute("applications", applicationService.findAllApplication());
	    }
	    
	    return "appResearch1";
	}
	@PostMapping("/appSearch/edit")
	public String editAdmin(@ModelAttribute Application application, 
			BindingResult bindingResult, ModelMap model) {
		//System.out.println(application.getApplicationType());
	    if (bindingResult.hasErrors()) {
	        return "error";
	    }
	    applicationService.updateApplication(application);
	    // 保存 Application
	   // applicationService.saveApplication(application);

	    return "redirect:/admin/appSearch";
	}
}
