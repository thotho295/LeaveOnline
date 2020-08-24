package dev.hulk.leave.form;

import lombok.Data;

@Data
public class AddEmployeeForm {
    private String inputName;
    private String inputEmail;
    private Integer managerId;
    private String role;
}
