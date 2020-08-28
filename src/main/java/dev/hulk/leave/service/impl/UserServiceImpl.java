package dev.hulk.leave.service.impl;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.Role;
import dev.hulk.leave.entity.User;
import dev.hulk.leave.form.AddEmployeeForm;
import dev.hulk.leave.repository.EmployeeRepository;
import dev.hulk.leave.repository.RoleRepository;
import dev.hulk.leave.repository.UserRepository;
import dev.hulk.leave.service.MailService;
import dev.hulk.leave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           EmployeeRepository employeeRepository, RoleRepository roleRepository, MailService mailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
    }

    @Override
    public void createNew(AddEmployeeForm form) {
        User user = new User();
        user.setUsername(form.getInputEmail());
        String password = UUID.randomUUID().toString();
        user.setPassHash(passwordEncoder.encode(password));

        Employee employee = employeeRepository.findOneByEmail(form.getInputEmail());

        user.setEmployee(employee);

        Role role = roleRepository.findRoleByName(form.getRole());

        user.setRole(role);

        userRepository.save(user);

        try {
            String message = "You have new account!\nUsername: " + form.getInputEmail() + ".\nPassword: " + password + "\nGo to login!";
            mailService.sendEmail(form.getInputEmail(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
