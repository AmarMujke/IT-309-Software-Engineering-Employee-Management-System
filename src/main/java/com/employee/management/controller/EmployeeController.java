package com.employee.management.controller;

import com.employee.management.model.Employees;
import com.employee.management.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    @GetMapping("/getEmployees")
    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    @GetMapping("/getEmployeeById/{id}")
    public Employees getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeRepository.getById(employeeId);
    }

    // Update employee
    @PutMapping("/updateEmployee/{id}")
    public Employees updateEmployee(@PathVariable Long id, @RequestBody Employees employee) {
        Employees existingEmployee = employeeRepository.getById(id);

        if(employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }
        if(employee.getJobTitle() != null) {
            existingEmployee.setJobTitle(employee.getJobTitle());
        }
        if(employee.getDepartmentId() != 0) {
            existingEmployee.setDepartmentId(employee.getDepartmentId());
        }
        if(employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if(employee.getPhoneNumber() != null) {
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        }
        if(employee.getHireDate() != null) {
            existingEmployee.setHireDate(employee.getHireDate());
        }
        if(employee.getSalary() != null) {
            existingEmployee.setSalary(employee.getSalary());
        }

        return employeeRepository.save(existingEmployee);
    }

    @DeleteMapping("deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employees employee = employeeRepository.getById(employeeId);
        employeeRepository.delete(employee);
    }

    // Create new employee
    @PostMapping("/createEmployee")
    public Employees createEmployee(@RequestBody Employees employee) {
        Employees newEmployee = new Employees();
        newEmployee.setName(employee.getName() != null ? employee.getName() : "Unknown");
        newEmployee.setJobTitle(employee.getJobTitle() != null ? employee.getJobTitle() : "Unknown");
        newEmployee.setDepartmentId(employee.getDepartmentId() > 0 ? employee.getDepartmentId() : 1);
        newEmployee.setEmail(employee.getEmail() != null ? employee.getEmail() : "");
        newEmployee.setPhoneNumber(employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "");
        newEmployee.setHireDate(employee.getHireDate() != null ? employee.getHireDate() : "Unknown");
        newEmployee.setSalary(employee.getSalary() != null ? employee.getSalary() : 0.0);
        return employeeRepository.save(newEmployee);
    }

    // Get employees by department id
    @GetMapping("/getEmployeesByDepartment/{departmentId}")
    public List<Employees> getEmployeesByDepartment(@PathVariable int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

}
