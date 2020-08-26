package dev.hulk.leave.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "on_work")
    @NotNull
    private boolean onWork;

    @OneToMany(mappedBy = "upperLevel")
    private List<Employee> lowerLevels;

    @ManyToOne
    @JoinColumn(name = "upper_level_id")
    private Employee upperLevel;

    @OneToMany(mappedBy = "employee")
    private List<LeaveRequest> leaveRequests;

    @OneToMany(mappedBy = "approver")
    private List<LeaveRequest> leaveRequestApprove;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
