package sg.edu.nus.spring_laps.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.AdminService;
import sg.edu.nus.spring_laps.service.DepartmentService;


@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private DepartmentService departmentService;
  @GetMapping
  public String AdminDashboard(Model model) {
      return "admin";
  }
  @GetMapping("/create")    //创建员工
  public String showCreateStaffForm(Model model, HttpSession session) {
      model.addAttribute("staff", new Staff());
      //Department d=new Department();
      List<Department> department=departmentService.findAllDepartment();
      System.out.print(department);
      List<Integer> hierarchy=adminService.findAllHierarchy();
      model.addAttribute("department",department);
      model.addAttribute("hierarchy",hierarchy);
      return "createStaff";
  }

  @PostMapping("/create")    //保存员工
  public String saveStaff(@ModelAttribute("staff") Staff staff,
                          BindingResult bindingResult, Model model) {
      if (bindingResult.hasErrors()) {
          return "createStaff";
      }
      adminService.saveStaff(staff);
      return "redirect:/admin/create";
  }
  
  @GetMapping("/department/edit")    //保存在一个部门编辑的员工
  public String editStaffFinished(Model model, HttpSession session) {
      return "searchresult1";
  }
  
  @PostMapping("/department/edit")    //保存在一个部门编辑的员工
  public String editStaff(@ModelAttribute("staff") Staff staff,
                          BindingResult bindingResult, Model model) {
      if (bindingResult.hasErrors()) {
          return "searchresult";
      }
      Department department = departmentService.findByName(staff.getDepartment().getName());
      staff.setDepartment(department);
      adminService.saveStaff(staff);
      return "redirect:/admin/department/edit";
  }
  
  @PostMapping("/search/searching")
  public String searchForStaff(@RequestParam(value = "query", required = false) String query, ModelMap model) {
      if (query != null && !query.isEmpty()) {
          Staff staffById = adminService.findStaffByUserId(query);
          List<Staff> staffByName = adminService.findStaffByName(query);

          Set<Staff> resultSet = new HashSet<>();
          if (staffById != null) resultSet.add(staffById);
          resultSet.addAll(staffByName);

          model.addAttribute("staff", resultSet);
      } else {
          model.addAttribute("staff", adminService.findAllStaff());
      }
      return "searchresult"; 
  }
  @PostMapping(value = "/search/searching/delete")  //删除员工
  public String deleteAdmin(@RequestParam("userId") String userId, Model model) {
	  System.out.println(userId);
	  adminService.deleteStaff(userId);
      return "redirect:/admin/department/edit";
  }
}

