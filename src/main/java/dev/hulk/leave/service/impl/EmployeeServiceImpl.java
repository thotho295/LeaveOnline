package dev.hulk.leave.service.impl;
import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.User;
import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.repository.EmployeeRepository;
import dev.hulk.leave.repository.UserRepository;
import dev.hulk.leave.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void createNew(AddEmployeeForm form) {

        Employee upperLevel = employeeRepository.findOneById(form.getManagerId());

        Employee employee = new Employee();
        employee.setName(form.getInputName());
        employee.setEmail(form.getInputEmail());
        employee.setUpperLevel(upperLevel);
        employee.setOnWork(true);

        User user = userRepository.findOneByUsername(form.getInputEmail());
        employee.setUser(user);

        employeeRepository.save(employee);

    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllLowerByEmployeeEmail(String name) {

        Employee upper = employeeRepository.findOneByEmail(name);

        return employeeRepository.findAllByUpperLevel(upper);
    }

    @Override
    public void deleteByEmail(String email) {
        employeeRepository.deleteByEmail(email);
    }
}
