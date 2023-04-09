package com.employee.management;

import com.employee.management.controller.PayrollController;
import com.employee.management.model.Payroll;
import com.employee.management.repo.PayrollRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PayrollControllerTest {

    @InjectMocks
    private PayrollController payrollController;

    @Mock
    private PayrollRepository payrollRepo;

    @Test
    public void testGetAllPayrolls() {
        // create some test data
        Payroll payroll1 = new Payroll();
        payroll1.setId(1L);
        payroll1.setEmployee_id(1);
        payroll1.setMonth(3);
        payroll1.setYear(2022);
        payroll1.setSalary(80000.00);
        payroll1.setTaxes(2000.00);

        Payroll payroll2 = new Payroll();
        payroll2.setId(2L);
        payroll2.setEmployee_id(2);
        payroll2.setMonth(3);
        payroll2.setYear(2022);
        payroll2.setSalary(100000.00);
        payroll2.setTaxes(2500.00);

        List<Payroll> payrolls = Arrays.asList(payroll1, payroll2);

        // mock the repository method to return the test data
        when(payrollRepo.findAll()).thenReturn(payrolls);

        // call the controller method
        List<Payroll> result = payrollController.getAllPayrolls();

        // verify that the result is the same as the test data
        assertEquals(payrolls, result);
    }

    @Test
    public void testGetPayrollById() {
        // create some test data
        Payroll payroll = new Payroll();
        payroll.setId(1L);
        payroll.setEmployee_id(1);
        payroll.setMonth(3);
        payroll.setYear(2022);
        payroll.setSalary(80000.00);
        payroll.setTaxes(2000.00);

        // mock the repository method to return the test data
        when(payrollRepo.findById(any())).thenReturn(Optional.of(payroll));

        // call the controller method
        Payroll result = payrollController.getPayrollById(1L);

        // verify that the result is the same as the test data
        assertEquals(payroll, result);
    }

    @Test
    public void testCreatePayroll() {
        // create some test data
        Payroll payroll = new Payroll();
        payroll.setId(1L);
        payroll.setEmployee_id(1);
        payroll.setMonth(3);
        payroll.setYear(2022);
        payroll.setSalary(80000.00);
        payroll.setTaxes(2000.00);

        // mock the repository method to return the saved entity
        when(payrollRepo.save(any())).thenReturn(payroll);

        // call the controller method
        Payroll result = payrollController.createPayroll(payroll);

        // verify that the result is the same as the test data
        assertEquals(payroll, result);
    }
}
