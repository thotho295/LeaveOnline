package dev.hulk.leave.controller;

import dev.hulk.leave.service.EmployeeService;
import dev.hulk.leave.service.RoleService;
import dev.hulk.leave.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final RoleService roleService;
    private  final UserService userService;
    private final EmployeeService employeeService;

    public HomeController(RoleService roleService, UserService userService, EmployeeService employeeService) {
        this.roleService = roleService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/")
    public String index(){
        return ("index");
    }

    @GetMapping(value = "/subject-report")
    public String report(){
        return "report";
    }

    @GetMapping(value = "/prepare")
    public String prepare(){

        roleService.createRole();

        return "index";
    }
}
