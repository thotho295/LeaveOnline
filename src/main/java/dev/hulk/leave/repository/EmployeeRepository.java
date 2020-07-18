package dev.hulk.leave.repository;

import dev.hulk.leave.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findOneById(int id);

    Employee findOneByEmail(String username);

    List<Employee> findAllByEmail(String email);
}
