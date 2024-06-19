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
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/Asearch")
	public String searchAdmin(Model model) {
		return "adminResearch";
	}
	@PostMapping(value = "/Asearch")
    public String search(@RequestParam("id") String id, ModelMap model) {
        model.addAttribute("admin", adminService.findAdminById(id));
        return "adminResearch1";
    }
	@PostMapping("/Asearch/edit")
	 public String editAdmin(@ModelAttribute("admin")@Valid Admin admin,
             BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
		return "adminResearch1";
		}
		adminService.saveAdmin(admin);
		return "redirect:/admin/Asearch";
		}
	 @PostMapping(value = "/Asearch/edit/delete")  //删除员工
	  public String deleteAdmin(@RequestParam("userId") String userId, Model model) {
		  System.out.println(userId);
		  adminService.deleteAdmin(userId);
	      return "redirect:/admin/department/edit";
	  }
	  @GetMapping("/Acreate")    //创建员工
	  public String showCreateStaffForm(ModelMap model, HttpSession session) {
	      model.addAttribute("admin", new Admin());
	      return "createAdmin";
	  }
	  @PostMapping("/Acreate")    //保存员工
	  public String saveStaff(@ModelAttribute("admin") Admin admin,
	                          BindingResult bindingResult, Model model) {
	      if (bindingResult.hasErrors()) {
	          return "createAdmin";
	      }
	      adminService.saveAdmin(admin);
	      return "redirect:/admin";
	  }
}
