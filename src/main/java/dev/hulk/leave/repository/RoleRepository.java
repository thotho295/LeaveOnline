package dev.hulk.leave.repository;

import dev.hulk.leave.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String role);
}
