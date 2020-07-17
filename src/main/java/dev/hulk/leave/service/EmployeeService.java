package dev.hulk.leave.service;

import dev.hulk.leave.dto.EmployeeDTO;
import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.form.RequestForm;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllManagers(String email);

    List<EmployeeDTO> getAll();

    void save(EmployeeDTO dto);

    void save(AddEmployeeForm form);

    Employee getByEmail(String email);
}
