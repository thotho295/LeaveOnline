package dev.hulk.leave.service;

import dev.hulk.leave.entity.LeaveRequest;
import dev.hulk.leave.form.AddLeaveRequestForm;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequest> getAll();

    List<LeaveRequest> getAllByEmail(String email);

    void addNewLeaveRequest(AddLeaveRequestForm form, String email);

    List<LeaveRequest> getAllEmployeeOff();

    List<LeaveRequest> getAllPendingRequest(String email);

    void approve(Integer id);

    void reject(Integer id);
}
