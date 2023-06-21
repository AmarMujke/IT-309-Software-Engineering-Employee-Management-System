package com.employee.management.model;

public class EmployeeRegistrationRequest {
    private Employees employee;
    private String password;

    // Getters and setters

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
