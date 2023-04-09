package com.employee.management.controller;

import com.employee.management.model.Department;
import com.employee.management.repo.DepartmentRepo;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepo departmentRepo;

    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @GetMapping("/getById/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentRepo.findById(id)
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
        return departmentRepo.save(newDepartment);
    }

    @PutMapping("/updateDepartment/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        return departmentRepo.save(department);
    }

    @DeleteMapping("deleteDepartment/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        departmentRepo.delete(department);
        return ResponseEntity.ok().build();
    }
}