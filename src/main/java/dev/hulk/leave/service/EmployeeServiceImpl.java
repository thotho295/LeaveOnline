package dev.hulk.leave.service;

import dev.hulk.leave.converter.EmployeeConverter;
import dev.hulk.leave.dto.EmployeeDTO;
import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private PasswordEncoder passwordEncoder;
    private EmployeeRepository employeeRepository;
    private EmployeeConverter employeeConverter;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setEmployeeConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllManagers(String email) {
        Employee employee = employeeRepository.findOneByEmail(email);
        Set<dev.hulk.leave.entity.Employee> employees = employee.getManagers();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();

        for (dev.hulk.leave.entity.Employee item : employees) {
            employeesDTO.add(employeeConverter.toDTO(item));
        }

        return employeesDTO;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<dev.hulk.leave.entity.Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeesDTO = new ArrayList<>();

        for (dev.hulk.leave.entity.Employee employee : employees) {
            employeesDTO.add(employeeConverter.toDTO(employee));
        }

        return employeesDTO;
    }

    @Override
    public void save(EmployeeDTO dto) {
        dev.hulk.leave.entity.Employee employee = employeeConverter.toEntity(dto);

        employeeRepository.save(employee);
    }

    @Override
    public void save(AddEmployeeForm form) {
        dev.hulk.leave.entity.Employee employee = new dev.hulk.leave.entity.Employee();

        int[] ids = form.getSelectManagers();

        if(ids != null){
            Set<dev.hulk.leave.entity.Employee> managers = new HashSet<>();
            for (int id : ids) {
                managers.add(employeeRepository.findOneById(id));
            }
            employee.setManagers(managers);
        }

        employee.setName(form.getInputName());
        employee.setEmail(form.getInputEmail());
        employee.setPassword(passwordEncoder.encode("password"));
        employee.setOnWork(true);
        employee.setRole("ROLE_USER");

        employeeRepository.save(employee);
    }

    @Override
    public dev.hulk.leave.entity.Employee getByEmail(String email) {
        return employeeRepository.findOneByEmail(email);
    }
}
