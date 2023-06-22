package com.employee.management.controller;

import com.employee.management.model.EmployeeRegistrationRequest;
import com.employee.management.model.Employees;
import com.employee.management.repo.EmployeePasswordRepository;
import com.employee.management.repo.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePasswordRepository employeePasswordRepository;

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
    @DeleteMapping("/deleteEmployee/{id}")
    @Transactional
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        try {
            // Delete employee's password first
            deleteEmployeePassword(employeeId);

            // Delete the employee
            employeeRepository.deleteById(employeeId);

            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee");
        }
    }

    private void deleteEmployeePassword(Long employeeId) {
        entityManager.createNativeQuery("DELETE FROM employee_passwords WHERE employee_id = :employeeId")
                .setParameter("employeeId", employeeId)
                .executeUpdate();
    }
    // Search employee
    @PostMapping("/search")
    public ResponseEntity<?> searchEmployeeByName(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.isEmpty()) {
            // Return a bad request response if the name is not provided
            return ResponseEntity.badRequest().body("Name is required");
        }

        // Perform the search operation based on the provided name
        List<Employees> searchResults = (List<Employees>) employeeRepository.findByName(name);

        if (searchResults.isEmpty()) {
            // Return a not found response if no employee is found
            return ResponseEntity.notFound().build();
        } else {
            // Return the search results as a success response
            return ResponseEntity.ok(searchResults);
        }
    }

    // search employee by email
    @PostMapping("/searchEmail")
    public ResponseEntity<?> searchEmployeeByEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            // Return a bad request response if the email is not provided
            return ResponseEntity.badRequest().body("Email is required");
        }

        // Perform the search operation based on the provided email
        Employees employee = employeeRepository.findByEmail(email);

        if (employee == null) {
            // Return a not found response if no employee is found
            return ResponseEntity.notFound().build();
        } else {
            // Return the employee as a success response
            return ResponseEntity.ok(employee);
        }
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
        if(employee.getAdmin() != null) {
            existingEmployee.setAdmin(employee.getAdmin());
        }

        return employeeRepository.save(existingEmployee);
    }

    // Get employees by department id
    @GetMapping("/getEmployeesByDepartment/{departmentId}")
    public List<Employees> getEmployeesByDepartment(@PathVariable int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> loginEmployee(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        // Get the employee by email
        Employees employee = employeeRepository.findByEmail(email);

        if (employee != null && employeeRepository.checkPassword((long) employee.getId().intValue(), password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    // Register
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeRegistrationRequest registrationRequest) {
        // Extract employee details from the registration request
        Employees employee = new Employees();
        employee.setName(registrationRequest.getEmployee().getName());
        employee.setJobTitle(registrationRequest.getEmployee().getJobTitle());
        employee.setDepartmentId(registrationRequest.getEmployee().getDepartmentId());
        employee.setEmail(registrationRequest.getEmployee().getEmail());
        employee.setPhoneNumber(registrationRequest.getEmployee().getPhoneNumber());
        employee.setHireDate(registrationRequest.getEmployee().getHireDate());
        employee.setSalary(registrationRequest.getEmployee().getSalary());
        employee.setAdmin(registrationRequest.getEmployee().getAdmin());

        // Extract the password from the registration request
        String password = registrationRequest.getPassword();

        // Check if the email already exists in the database
        Employees existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployee != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        // Save the employee and password
        employeeRepository.save(employee);
        employeePasswordRepository.savePassword(employee.getId(), password);

        return ResponseEntity.ok("Registration successful");
    }
}