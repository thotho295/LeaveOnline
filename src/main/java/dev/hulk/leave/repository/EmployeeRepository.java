package dev.hulk.leave.repository;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findOneById(Integer id);

    Employee findOneByEmail(String username);

    void deleteByEmail(String email);

    Employee findOneByUser(User user);

    List<Employee> findAllByUpperLevel(Employee upper);
}
