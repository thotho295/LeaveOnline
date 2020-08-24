package dev.hulk.leave.repository;

import dev.hulk.leave.entity.Employee;
import dev.hulk.leave.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {
    List<LeaveRequest> findAllByEmployee(Employee employee);

    List<LeaveRequest> findAllByEmployeeAndStartGreaterThan(Employee employee, Date start);

    List<LeaveRequest> findAllByStatusAndStartLessThanEqualAndEndGreaterThanEqual(String status, Date start, Date end);

    List<LeaveRequest> findAllByEmployeeAndStatus(Employee employee, String pending);

    LeaveRequest findOneById(Integer id);
}
