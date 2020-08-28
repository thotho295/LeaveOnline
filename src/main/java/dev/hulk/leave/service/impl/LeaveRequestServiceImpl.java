package dev.hulk.leave.service.impl;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.LeaveRequest;
import dev.hulk.leave.entity.Role;
import dev.hulk.leave.entity.User;
import dev.hulk.leave.form.AddLeaveRequestForm;
import dev.hulk.leave.repository.EmployeeRepository;
import dev.hulk.leave.repository.LeaveRequestRepository;
import dev.hulk.leave.repository.RoleRepository;
import dev.hulk.leave.repository.UserRepository;
import dev.hulk.leave.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository,
                                   EmployeeRepository employeeRepository,
                                   RoleRepository roleRepository,
                                   UserRepository userRepository, JavaMailSender javaMailSender) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<LeaveRequest> getAll() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getAllByEmail(String email) {

        Employee employee = employeeRepository.findOneByEmail(email);

        return leaveRequestRepository.findAllByEmployee(employee);
    }

    @Override
    public void addNewLeaveRequest(AddLeaveRequestForm form, String email) {
        LeaveRequest leaveRequest = new LeaveRequest();

        Employee employee = employeeRepository.findOneByEmail(email);

        leaveRequest.setStart(form.getStartDateTime());
        leaveRequest.setEnd(form.getEndDateTime());
        leaveRequest.setReason(form.getReasonTextArea());
        leaveRequest.setStatus("Pending");
        leaveRequest.setEmployee(employee);

        Employee approver = employee.getUpperLevel();
        if(approver == null){
            Role role = roleRepository.findRoleByName("ROLE_HR");
            User user = userRepository.findOneByRole(role);
            approver = employeeRepository.findOneByUser(user);
        }

        leaveRequest.setApprover(approver);

        leaveRequestRepository.save(leaveRequest);

        try{
            String message = employee.getName() + "sent a new request for leave \n Check it now";
            sendEmail(employee.getUpperLevel().getEmail(), message);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<LeaveRequest> getAllEmployeeOff() {

        return leaveRequestRepository.findAllByStatusAndStartLessThanEqualAndEndGreaterThanEqual("Accepted", new Date(), new Date());
    }

    @Override
    public List<LeaveRequest> getAllPendingRequest(String email) {
        Employee approver = employeeRepository.findOneByEmail(email);
        List<Employee> employees = approver.getLowerLevels();
        List<LeaveRequest> leaveRequests = new ArrayList<>();

        for(Employee employee : employees){
            leaveRequests.addAll(leaveRequestRepository.findAllByEmployeeAndStatus(employee, "Pending"));
        }

        return leaveRequests;
    }

    @Override
    public void approve(Integer id, String email) {
        LeaveRequest leaveRequest = leaveRequestRepository.findOneById(id);

        leaveRequest.setStatus("Approved");

        leaveRequestRepository.save(leaveRequest);

        Employee employee = employeeRepository.findOneByEmail(email);

        try {
            String message = "Your Leave Request have been Accepted by " + employee.getName() + "\n Check it!";
            sendEmail(leaveRequest.getEmployee().getEmail(), message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void reject(Integer id, String email) {
        LeaveRequest leaveRequest = leaveRequestRepository.findOneById(id);

        leaveRequest.setStatus("Rejected");

        leaveRequestRepository.save(leaveRequest);

        Employee employee = employeeRepository.findOneByEmail(email);

        try {
            String message = "Your Leave Request have been Rejected by " + employee.getName() + "\n Check it!";
            sendEmail(leaveRequest.getEmployee().getEmail(), message);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<LeaveRequest> getAllPendingRequest() {
        return leaveRequestRepository.findAllByStatus("Pending");
    }

    @Override
    public void deleteById(Integer requestId) {
        leaveRequestRepository.deleteById(requestId);
    }

    void sendEmail(String destination, String message){
        SimpleMailMessage mgs = new SimpleMailMessage();

        mgs.setTo(destination);
        mgs.setSubject("Leave Request Notification");
        mgs.setText(message);

        javaMailSender.send(mgs);
        System.out.println("Mail sent!");
    }
}
