package dev.hulk.leave.service;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.form.AddEmployeeForm;

import java.util.List;

public interface EmployeeService {

    void createNew(AddEmployeeForm form);

    List<Employee> getAll();

    List<Employee> getAllLowerByEmployeeEmail(String name);

    void deleteByEmail(String email);

    Employee getOneByEmail(String email);
}
