package dev.hulk.leave.service;

import dev.hulk.leave.form.AddEmployeeForm;

public interface UserService {
    void createNew(AddEmployeeForm form);

    void deleteByEmail(String email);

    void changePassword(String email, String newPass);
}
