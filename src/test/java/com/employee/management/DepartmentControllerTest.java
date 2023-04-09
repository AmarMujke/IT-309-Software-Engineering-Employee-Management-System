package com.employee.management;

import com.employee.management.controller.DepartmentController;
import com.employee.management.model.Department;
import com.employee.management.repo.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    public void testGetAllDepartments() {
        // create some test data
        Department department1 = new Department();
        department1.setName("Sales");
        department1.setDescription("Responsible for selling products and services");
        department1.setManager("John Doe");

        Department department2 = new Department();
        department2.setName("Marketing");
        department2.setDescription("Responsible for promoting the company and its products");
        department2.setManager("Jane Smith");

        List<Department> departments = Arrays.asList(department1, department2);

        // mock the repository method to return the test data
        when(departmentRepository.findAll()).thenReturn(departments);

        // call the controller method
        List<Department> result = departmentController.getAllDepartments();

        // verify that the result is the same as the test data
        assertEquals(departments, result);
    }

    @Test
    public void testGetDepartmentById() {
        // create some test data
        Department department = new Department();
        department.setId(1);
        department.setName("Sales");
        department.setDescription("Responsible for selling products and services");
        department.setManager("John Doe");

        // mock the repository method to return the test data
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        // call the controller method
        Department result = departmentController.getDepartmentById(1L);

        // verify that the result is the same as the test data
        assertEquals(department, result);
    }

    @Test
    public void testCreateDepartment() {
        // create some test data
        Department department = new Department();
        department.setName("Sales");
        department.setDescription("Responsible for selling products and services");
        department.setManager("John Doe");

        // mock the repository method to return the saved entity
        when(departmentRepository.save(any())).thenReturn(department);

        // call the controller method
        Department result = departmentController.createDepartment(department);

        // verify that the result is the same as the test data
        assertEquals(department, result);
    }
}