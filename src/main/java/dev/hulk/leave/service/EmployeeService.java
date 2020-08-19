package dev.hulk.leave.service;

import dev.hulk.leave.dto.EmployeeDTO;
import dev.hulk.leave.form.AddEmployeeForm;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllManagers(String email);

    List<EmployeeDTO> getAll();

    void save(EmployeeDTO dto);

    void save(AddEmployeeForm form);

    dev.hulk.leave.entity.Employee getByEmail(String email);
}
