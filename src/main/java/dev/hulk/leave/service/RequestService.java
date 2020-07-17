package dev.hulk.leave.service;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.LeaveRequest;
import dev.hulk.leave.form.RequestForm;

import java.util.List;

public interface RequestService {
    List<LeaveRequest> getAll();

    List<LeaveRequest> getAllByEmployeeEmail(String email);

    void save(RequestForm form, String email);

    List<LeaveRequest> getAllByApproverEmail(String email);
}
