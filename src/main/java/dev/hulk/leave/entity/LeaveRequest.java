package dev.hulk.leave.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_time")
    @NotNull
    private Date start;

    @Column(name = "end_time")
    @NotNull
    private Date end;

    @Column(name = "reason")
    @NotNull
    private String reason;

    @Column(name = "status")
    @NotNull
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    private Employee employee;

    @ManyToMany
    @JoinTable(
            name = "request_approver",
            joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id")
    )
    private Set<Employee> approvers;

    public int getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Employee> getApprovers() {
        return approvers;
    }

    public void setApprovers(Set<Employee> approvers) {
        this.approvers = approvers;
    }
}
