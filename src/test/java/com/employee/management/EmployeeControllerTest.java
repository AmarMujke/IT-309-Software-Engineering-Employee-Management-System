package com.employee.management;

import com.employee.management.controller.EmployeeController;
import com.employee.management.model.EmployeeRegistrationRequest;
import com.employee.management.model.Employees;
import com.employee.management.repo.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetAllEmployees() {
        // create some test data
        Employees employee1 = new Employees();
        employee1.setName("John Doe");
        employee1.setJobTitle("Software Engineer");
        employee1.setDepartmentId(1);
        employee1.setEmail("john.doe@example.com");
        employee1.setPhoneNumber("+1-555-555-1234");
        employee1.setHireDate("2021-03-01");
        employee1.setSalary(80000.00);

        Employees employee2 = new Employees();
        employee2.setName("Jane Smith");
        employee2.setJobTitle("Project Manager");
        employee2.setDepartmentId(2);
        employee2.setEmail("jane.smith@example.com");
        employee2.setPhoneNumber("+1-555-555-5678");
        employee2.setHireDate("2020-01-01");
        employee2.setSalary(100000.00);

        List<Employees> employees = Arrays.asList(employee1, employee2);

        // mock the repository method to return the test data
        when(employeeRepository.findAll()).thenReturn(employees);

        // call the controller method
        List<Employees> result = employeeController.getAllEmployees();

        // verify that the result is the same as the test data
        assertEquals(employees, result);
    }

}
