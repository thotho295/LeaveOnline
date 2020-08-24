package dev.hulk.leave.repository;

import dev.hulk.leave.entity.Role;
import dev.hulk.leave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByUsername(String username);

    User findOneByRole(Role role);

    void deleteByUsername(String email);
}
