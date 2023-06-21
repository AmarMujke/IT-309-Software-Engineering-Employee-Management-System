package com.employee.management.controller;

import com.employee.management.model.Department;
import com.employee.management.repo.DepartmentRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
    }

    @PostMapping("/create")
    public Department createDepartment(@RequestBody Department department) {
        Department newDepartment = new Department();
        if(department.getName() != null) {
            newDepartment.setName(department.getName());
        }
        if(department.getDescription() != null) {
            newDepartment.setDescription(department.getDescription());
        }
        if(department.getManager() != null) {
            newDepartment.setManager(department.getManager());
        }
        return departmentRepository.save(newDepartment);
    }

    @PutMapping("/updateDepartment/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        department.setManager(departmentDetails.getManager());
        return departmentRepository.save(department);
    }

    @DeleteMapping("deleteDepartment/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        departmentRepository.delete(department);
        return ResponseEntity.ok().build();
    }
}