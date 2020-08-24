package dev.hulk.leave.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddEmployeeForm {
    private String inputName;
    private String inputEmail;
    private Integer managerId;
    private String role;
}
