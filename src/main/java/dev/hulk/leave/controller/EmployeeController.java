package dev.hulk.leave.controller;

import dev.hulk.leave.entity.LeaveRequest;
import dev.hulk.leave.form.AddLeaveRequestForm;
import dev.hulk.leave.service.EmployeeService;
import dev.hulk.leave.service.LeaveRequestService;
import dev.hulk.leave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final LeaveRequestService leaveRequestService;
    private final EmployeeService employeeService;
    private final UserService userService;

    @Autowired
    public EmployeeController(LeaveRequestService leaveRequestService, EmployeeService employeeService, UserService userService) {
        this.leaveRequestService = leaveRequestService;
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @GetMapping(value = "/dashboard")
    public String dashboard(Model model, Principal principal) {

        try {
            String email = principal.getName();

            List<LeaveRequest> leaveRequests = leaveRequestService.getAllByEmail(email);
            int daysOffThisMonth = 0;
            int daysOffThisYear = 0;
            int acceptedRequests = 0;
            int pendingRequests = 0;
            int rejectedRequests = 0;

            for (LeaveRequest leaveRequest : leaveRequests) {
                switch (leaveRequest.getStatus()) {
                    case "Accepted":
                        acceptedRequests++;
                        break;
                    case "Rejected":
                        rejectedRequests++;
                        break;
                    case "Pending":
                        pendingRequests++;
                        break;
                }
            }

            model.addAttribute("requests", leaveRequests);
            model.addAttribute("daysOffThisMonth", daysOffThisMonth);
            model.addAttribute("daysOffThisYear", daysOffThisYear);
            model.addAttribute("acceptedRequests", acceptedRequests);
            model.addAttribute("pendingRequests", pendingRequests);
            model.addAttribute("rejectedRequests", rejectedRequests);
            model.addAttribute("currentEmployee", employeeService.getOneByEmail(email));
            model.addAttribute("addLeaveRequestForm", new AddLeaveRequestForm());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "employee/dashboard";
    }

    @PostMapping(value = "/dashboard/add-leave")
    public String addLeave(@ModelAttribute AddLeaveRequestForm form, Principal principal) {
        try {
            leaveRequestService.addNewLeaveRequest(form, principal.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/employee/dashboard";
    }

    @GetMapping(value = "/colleagues")
    public String colleagues(Model model) {
        try {
            model.addAttribute("colleaguesOff", leaveRequestService.getAllEmployeeOff());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "employee/colleagues";
    }

    @GetMapping(value = "/lower-level")
    public String lowerLevel(Model model, Principal principal) {
        try {
            model.addAttribute("lowers", employeeService.getAllLowerByEmployeeEmail(principal.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "employee/lower_level";
    }

    @GetMapping(value = "/approve")
    public String approve(Model model, Principal principal) {
        try {
            model.addAttribute("leaves", leaveRequestService.getAllPendingRequest(principal.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "employee/approve";
    }

    @PostMapping(value = "/approve/approve/{id}")
    public String approveLeave(@PathVariable Integer id, Principal principal) {
        try {
            leaveRequestService.approve(id, principal.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/employee/approve";
    }

    @PostMapping(value = "/approve/reject/{id}")
    public String rejectLeave(@PathVariable Integer id, Principal principal) {
        try{
            leaveRequestService.reject(id, principal.getName());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/employee/approve";
    }

    @GetMapping(value = "/change-password")
    public String changePasswordView() {
        return "change_password";
    }

    @PostMapping(value = "/change-password")
    public String changePassword(Principal principal, @RequestParam(name = "password2") String newPass) {
        try{
            userService.changePassword(principal.getName(), newPass);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/employee/dashboard";
    }
}
