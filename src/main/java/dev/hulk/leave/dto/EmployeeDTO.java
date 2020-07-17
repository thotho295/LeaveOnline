package dev.hulk.leave.dto;

import dev.hulk.leave.entity.Employee;

import java.util.Set;

public class EmployeeDTO {
    private int id;

    public int getId() {
        return id;
    }

    private String name;
    private String email;
    private boolean onWork;
    private String password;
    private Set<EmployeeDTO> managers;

    public void setId(int id) {
        this.id = id;
    }

    public Set<EmployeeDTO> getManagers() {
        return managers;
    }

    public void setManagers(Set<EmployeeDTO> managers) {
        this.managers = managers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnWork() {
        return onWork;
    }

    public void setOnWork(boolean onWork) {
        this.onWork = onWork;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
