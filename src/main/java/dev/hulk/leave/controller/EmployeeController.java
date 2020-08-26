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

import javax.servlet.http.HttpServletRequest;
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

        String email = principal.getName();

        List<LeaveRequest> leaveRequests = leaveRequestService.getAllByEmail(email);
        int daysOffThisMonth = 0;
        int daysOffThisYear = 0;
        int acceptedRequests = 0;
        int pendingRequests = 0;
        int rejectedRequests = 0;

        for(LeaveRequest leaveRequest : leaveRequests){
            switch (leaveRequest.getStatus()){
                case "Accepted":
                    acceptedRequests++;

//                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//
//                    Date current = new Date();
//                    Date start = format.parse(leaveRequest.getStart().toString());
//                    Date end = format.parse(leaveRequest.getEnd().toString());
//
//                    daysOffThisYear += Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();

                    break;
                case "Rejected" :
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
        model.addAttribute("addLeaveRequestForm", new AddLeaveRequestForm());

        return "employee/dashboard";
    }

    @PostMapping(value = "/dashboard/add-leave")
    public String addLeave(@ModelAttribute AddLeaveRequestForm form, Principal principal){

        leaveRequestService.addNewLeaveRequest(form, principal.getName());

        return "redirect:/employee/dashboard";
    }

    @GetMapping(value = "/colleagues")
    public String colleagues(Model model){
        model.addAttribute("colleaguesOff", leaveRequestService.getAllEmployeeOff());

        return "employee/colleagues";
    }

    @GetMapping(value = "/lower-level")
    public String lowerLevel(Model model, Principal principal){

        model.addAttribute("lowers", employeeService.getAllLowerByEmployeeEmail(principal.getName()));

        return "employee/lower_level";
    }

    @GetMapping(value = "/approve")
    public String approve(Model model, Principal principal){

        model.addAttribute("leaves", leaveRequestService.getAllPendingRequest(principal.getName()));

        return "employee/approve";
    }

    @PostMapping(value = "/approve/approve/{id}")
    public String approveLeave(@PathVariable Integer id){

        leaveRequestService.approve(id);

        return "redirect:/employee/approve";
    }

    @PostMapping(value = "/approve/reject/{id}")
    public String rejectLeave(@PathVariable Integer id){

        leaveRequestService.reject(id);

        return "redirect:/employee/approve";
    }

    @GetMapping(value = "/change-password")
    public String changePasswordView(){
        return "change_password";
    }

    @PostMapping(value = "/change-password")
    public String changePassword(Principal principal, @RequestParam(name = "password2") String newPass){

        userService.changePassword(principal.getName(), newPass);

        return "redirect:/employee/dashboard";
    }
}
