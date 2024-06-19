package sg.edu.nus.spring_laps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.service.DepartmentService;


@Controller
@RequestMapping("/admin")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	 
	    @GetMapping(value = "/department")    //查看所有部门
	    public String displayDepartment( Model model) {
	    	//System.out.println(departmentService.findAllDepartment());
	        model.addAttribute("department", departmentService.findAllDepartment());
	        return "displayDepartment";
	    }
	    
	  @PostMapping(value = "/department/date")    //编辑年假天数
	  public String editDate(@ModelAttribute("department") Department department, 
			  BindingResult bindingResult, Model model) {
	      if(bindingResult.hasErrors()) {
	          return "error";
	      }
		 // System.out.println(department);
	      departmentService.saveDepartment(department);
	      return "redirect:/admin/department";
	  }
	  
	  @GetMapping("/department/search")    //搜索一个部门的所有员工
	  public String AdminSearch(@RequestParam(value = "departmentId", required = false) String departmentId,
			  Model model) {
		  System.out.println(departmentId);
		  model.addAttribute("staff", departmentService.findAllStaff(departmentId));
	      return "searchresult";
	  }
}
