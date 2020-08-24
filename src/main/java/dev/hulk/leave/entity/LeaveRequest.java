package dev.hulk.leave.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leave_request")
@Getter
@Setter
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "start_date")
    @NotNull
    private Date start;

    @Column(name = "end_date")
    @NotNull
    private Date end;

    @Column(name = "reason")
    @NotNull
    private String reason;

    @Column(name = "status")
    @NotNull
    private String status;

    @ManyToOne
    @NotNull
    private Employee employee;

    @ManyToOne
    @NotNull
    private Employee approver;
}
