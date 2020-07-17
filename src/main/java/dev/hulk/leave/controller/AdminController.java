package dev.hulk.leave.controller;

import dev.hulk.leave.dto.EmployeeDTO;
import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.service.EmployeeService;
import dev.hulk.leave.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller()
public class AdminController {

    private EmployeeService employeeService;
    private RequestService requestService;

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/admin/employees")
    public String employees(Model model){

        List<EmployeeDTO> employeesDTO = employeeService.getAll();

        model.addAttribute("addEmployeeForm", new AddEmployeeForm());
        model.addAttribute("employees", employeesDTO);

        return "admin/employee";
    }

    @PostMapping(value = "/admin/employee")
    public String employee(@ModelAttribute("addEmployeeForm") AddEmployeeForm form){

        employeeService.save(form);

        return "redirect:/admin/employees";
    }

    @GetMapping(value = "/admin/home")
    public String home(Model model){
        return "admin/home";
    }

    @GetMapping(value = "/admin/requests")
    public String request(Model model){

        model.addAttribute("requests", requestService.getAll());

        return "admin/request";
    }
}
