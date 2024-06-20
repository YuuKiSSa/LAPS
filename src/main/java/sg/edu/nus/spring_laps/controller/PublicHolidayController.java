package sg.edu.nus.spring_laps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.PublicHoliday;
import sg.edu.nus.spring_laps.service.PublicHolidayService;

@Controller
@RequestMapping("/admin")
public class PublicHolidayController {

	@Autowired
	private PublicHolidayService publicHolidayService;
	@GetMapping("/PublicHoliday")
	public String searchHoliday(ModelMap model) {
		List<PublicHoliday> p=publicHolidayService.findAllHoliday();
		model.addAttribute("holiday", p);
		return "PublicHoliday";
	}
	
	@GetMapping("/PublicHoliday/create")
	public String createHoliday(ModelMap model) {
		model.addAttribute("holiday", new PublicHoliday());
		model.addAttribute("holidayList", publicHolidayService.findAllHoliday()); 
		return "CreateHoliday";
	}
	
	@PostMapping("/PublicHoliday/create")
	public String saveHoliday(@ModelAttribute PublicHoliday publicHoliday,
            BindingResult bindingResult, Model model) {
		System.out.println(publicHoliday);
		if (bindingResult.hasErrors()) {
			return "error";
			}
		publicHolidayService.saveHoliday(publicHoliday);
			return "redirect:/admin/PublicHoliday";
			}
	
	
	
}
