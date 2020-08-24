package dev.hulk.leave.service.impl;

import dev.hulk.leave.entity.Role;
import dev.hulk.leave.repository.RoleRepository;
import dev.hulk.leave.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole() {
        Role role_hr = new Role("ROLE_HR");
        Role role_employee = new Role("ROLE_EMPLOYEE");

        roleRepository.save(role_hr);
        roleRepository.save(role_employee);
    }
}
