package dev.hulk.leave.service.impl;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.Role;
import dev.hulk.leave.entity.User;
import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.repository.EmployeeRepository;
import dev.hulk.leave.repository.RoleRepository;
import dev.hulk.leave.repository.UserRepository;
import dev.hulk.leave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createNew(AddEmployeeForm form) {
        User user = new User();
        user.setUsername(form.getInputEmail());
        user.setPassHash(passwordEncoder.encode("password"));

        Employee employee = employeeRepository.findOneByEmail(form.getInputEmail());

        user.setEmployee(employee);

        Role role = roleRepository.findOneByName(form.getRole());

        user.setRole(role);

        userRepository.save(user);

    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByUsername(email);
    }

    @Override
    public void changePassword(String email, String newPass) {
        User user = userRepository.findOneByUsername(email);

        user.setPassHash(passwordEncoder.encode(newPass));

        userRepository.save(user);
    }
}
