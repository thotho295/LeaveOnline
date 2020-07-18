package dev.hulk.leave.repository;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findAllByEmployee(Employee employee);

    LeaveRequest findOneById(int id);
}
