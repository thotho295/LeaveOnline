package dev.hulk.leave.controller;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.service.EmployeeService;
import dev.hulk.leave.service.RequestService;
import dev.hulk.leave.form.RequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class EmployeeController {

    private RequestService requestService;
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setRequestService(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/employee")
    public String requests(){
        return "redirect:/employee/home";
    }

    @GetMapping(value = "/employee/home")
    public String requests(Model model, Principal principal) {

        String email = principal.getName();

        System.out.println(email);

        System.out.println();

        model.addAttribute("requests", requestService.getAllByEmployeeEmail(email));
        model.addAttribute("currentUser", employeeService.getByEmail(email));
        model.addAttribute("requestForm", new RequestForm());

        return "employee/home";
    }

    @PostMapping(value = "/employee/request")
    public String request(@ModelAttribute RequestForm form, Principal principal) {

        String email = principal.getName();

        requestService.save(form, email);

        return "redirect:/employee/home";
    }

    @GetMapping(value = "/employee/request/update")
    public String acceptRequest(@RequestParam(name = "request_id") int id, @RequestParam(name = "status") String status){

        requestService.updateStatus(id, status);

        return "redirect:/employee/approves";
    }

    @PostMapping(value = "/employee/request/delete")
    public String request(@RequestParam(name = "request_id") int id){

        requestService.delete(id);

        return "redirect:/employee/home";
    }

    @GetMapping(value = "/employee/approves")
    public String approves(Model model, Principal principal) {

        String email = principal.getName();
        model.addAttribute("requests", requestService.getAllPendingByApproverEmail(email));

        return "employee/approve";
    }
}
