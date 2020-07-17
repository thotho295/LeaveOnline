package dev.hulk.leave.service;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.LeaveRequest;
import dev.hulk.leave.form.RequestForm;
import dev.hulk.leave.repository.EmployeeRepository;
import dev.hulk.leave.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {

    private RequestRepository requestRepository;

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setRequestRepository(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<LeaveRequest> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getAllByEmployeeEmail(String email) {
        Employee employee = employeeRepository.findOneByEmail(email);
        List<LeaveRequest> requests = requestRepository.findAllByEmployee(employee);
        return requests;
    }

    @Override
    public void save(RequestForm form, String email) {
        LeaveRequest request = new LeaveRequest();
        request.setStart(form.getStartDateTime());
        request.setEnd(form.getEndDateTime());
        request.setReason(form.getReasonTextArea());
        request.setEmployee(employeeRepository.findOneByEmail(email));
        request.setStatus("PENDING");

        int[] ids = form.getSelectApprovers();

        if(ids != null){
            Set<Employee> approvers = new HashSet<>();
            for (int id : ids) {
                approvers.add(employeeRepository.findOneById(id));
            }
            request.setApprovers(approvers);
        }

        requestRepository.save(request);
    }

    @Override
    public List<LeaveRequest> getAllByApproverEmail(String email) {
        List<LeaveRequest> requests = requestRepository.findAll();

        List<LeaveRequest> results = new ArrayList<>();

        for(LeaveRequest request : requests){
            Employee employee = employeeRepository.findOneByEmail(email);
            if(request.getApprovers().contains(employee)){
                results.add(request);
            }
        }

        return results;
    }
}
