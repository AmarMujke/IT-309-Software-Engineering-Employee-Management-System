package com.employee.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_passwords")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeePasswords {

    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    private String password;

    // Getters and setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
