package dev.hulk.leave.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @NonNull
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
