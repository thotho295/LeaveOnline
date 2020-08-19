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
    private Set<Employee> managers;
    private Set<Employee> employees;

    public void setId(int id) {
        this.id = id;
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

    public Set<Employee> getManagers() {
        return managers;
    }

    public void setManagers(Set<Employee> managers) {
        this.managers = managers;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
