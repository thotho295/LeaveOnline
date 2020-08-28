package dev.hulk.leave.controller;

import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.service.EmployeeService;
import dev.hulk.leave.service.LeaveRequestService;
import dev.hulk.leave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/hr")
public class HRController {

    private final EmployeeService employeeService;
    private final LeaveRequestService leaveRequestService;
    private final UserService userService;

    @Autowired
    public HRController(EmployeeService employeeService, LeaveRequestService leaveRequestService, UserService userService) {
        this.employeeService = employeeService;
        this.leaveRequestService = leaveRequestService;
        this.userService = userService;
    }

    @GetMapping(value = "/dashboard")
    public String home(Model model) {
        try {
            model.addAttribute("employees", employeeService.getAll());
            model.addAttribute("pending", leaveRequestService.getAllPendingRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "hr/dashboard";
    }

    @GetMapping(value = "/employees")
    public String colleagues(Model model) {
        try {
            model.addAttribute("form", new AddEmployeeForm());
            model.addAttribute("employees", employeeService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "hr/employees";
    }

    @GetMapping(value = "/requests")
    public String requests(Model model) {
        try {
            model.addAttribute("requests", leaveRequestService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "hr/requests";
    }

    @PostMapping(value = "/requests/delete")
    public String deleteRequest(@RequestParam(name = "request_id") Integer requestId) {
        try {
            leaveRequestService.deleteById(requestId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/hr/requests";
    }

    @PostMapping(value = "/add-employee")
    public String addEmployeeAndCreateNewAccount(@ModelAttribute AddEmployeeForm form) {
        try {
            userService.createNew(form);
            employeeService.createNew(form);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/hr/employees";
    }

    @PostMapping(value = "/employees/delete/{email}")
    public String deleteEmployee(@PathVariable String email) {
        try {
            employeeService.deleteByEmail(email);
            userService.deleteByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/hr/employees";
    }


}
