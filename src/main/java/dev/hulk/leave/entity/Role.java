package dev.hulk.leave.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // Ko lien quan, lombok thoi
    private Integer id;

    @NonNull
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
