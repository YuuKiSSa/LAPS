package sg.edu.nus.spring_laps.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.spring_laps.model.*;
import sg.edu.nus.spring_laps.service.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationTypeService applicationTypeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PublicHolidayService publicHolidayService;

    @GetMapping("/Asearch")
    public String searchAdmin(Model model) {
        return "admin/adminResearch";
    }
    @PostMapping(value = "/Asearch")
    public String search(@RequestParam("id") String id, ModelMap model) {
        model.addAttribute("admin", adminService.findAdminById(id));
        return "admin/adminResearch1";
    }
    @PostMapping("/Asearch/edit")
    public String editAdmin(@ModelAttribute("admin")@Valid Admin admin,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/adminResearch1";
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
        return "admin/createAdmin";
    }
    @PostMapping("/Acreate")    //保存员工
    public String saveStaff(@ModelAttribute("admin") Admin admin,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/createAdmin";
        }
        adminService.saveAdmin(admin);
        return "redirect:/admin";
    }
    @GetMapping("/appSearch")
    public String searchApplication(ModelMap model) {
        return "admin/appResearch";
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

        return "admin/appResearch1";
    }
    @PostMapping("/appSearch/edit")
    public String editAdmin(@ModelAttribute Application application,
                            BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "admin/error";
        }
        applicationService.updateApplication(application);

        return "redirect:/admin/appSearch";
    }
    @GetMapping(value = "/department")    //查看所有部门
    public String displayDepartment(Model model) {
        model.addAttribute("department", departmentService.findAllDepartment());
        return "admin/displayDepartment";
    }

    @PostMapping(value = "/department/date")    //编辑年假天数
    public String editDate(@ModelAttribute("department") Department department,
                           BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "admin/error";
        }
        // System.out.println(department);
        departmentService.saveDepartment(department);
        return "redirect:/admin/department";
    }

    @GetMapping("/department/search")    //搜索一个部门的所有员工
    public String AdminSearch(@RequestParam(value = "departmentId", required = false) String departmentId,
                              Model model) {
        model.addAttribute("staff", departmentService.findAllStaff(departmentId));
        return "admin/searchresult";
    }

    @GetMapping("/PublicHoliday")
    public String searchHoliday(ModelMap model) {
        List<PublicHoliday> p=publicHolidayService.findAllHoliday();
        model.addAttribute("holiday", p);
        return "admin/PublicHoliday";
    }

    @GetMapping("/PublicHoliday/create")
    public String createHoliday(ModelMap model) {
        model.addAttribute("holiday", new PublicHoliday());
        model.addAttribute("holidayList", publicHolidayService.findAllHoliday());
        return "admin/CreateHoliday";
    }

    @PostMapping("/PublicHoliday/create")
    public String saveHoliday(@ModelAttribute PublicHoliday publicHoliday,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/error";
        }
        publicHolidayService.saveHoliday(publicHoliday);
        return "redirect:/admin/PublicHoliday";
    }

    @GetMapping
    public String AdminDashboard(Model model) {
        return "admin/admin";
    }
    @GetMapping("/create")    //创建员工
    public String showCreateStaffForm(Model model, HttpSession session) {
        model.addAttribute("staff", new Staff());
        //Department d=new Department();
        List<Department> department=departmentService.findAllDepartment();
        List<Integer> hierarchy=adminService.findAllHierarchy();
        model.addAttribute("department",department);
        model.addAttribute("hierarchy",hierarchy);
        return "admin/createStaff";
    }

    @PostMapping("/create")    //保存员工
    public String saveStaff(@ModelAttribute("staff") Staff staff,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/createStaff";
        }
        adminService.saveStaff(staff);
        return "redirect:/admin/create";
    }

    @GetMapping("/department/edit")    //保存在一个部门编辑的员工
    public String editStaffFinished(Model model, HttpSession session) {
        return "admin/searchresult1";
    }

    @PostMapping("/department/edit")    //保存在一个部门编辑的员工
    public String editStaff(@ModelAttribute("staff") Staff staff,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/searchresult";
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
        return "admin/searchresult";
    }
    @PostMapping(value = "/search/searching/delete")  //删除员工
    public String deleteStaff(@RequestParam("userId") String userId, Model model) {
        adminService.deleteStaff(userId);
        return "redirect:/admin/department/edit";
    }
}
