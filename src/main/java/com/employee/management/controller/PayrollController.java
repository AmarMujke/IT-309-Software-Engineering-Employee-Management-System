package com.employee.management.controller;

import com.employee.management.model.Payroll;
import com.employee.management.repo.PayrollRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollRepository payrollRepo;

    @GetMapping("/getAll")
    public List<Payroll> getAllPayrolls() {
        return payrollRepo.findAll();
    }

    @GetMapping("/getById/{id}")
    public Payroll getPayrollById(@PathVariable Long id) {
        return payrollRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id " + id));
    }

    @PostMapping("/create")
    public Payroll createPayroll(@RequestBody Payroll payroll) {
        return payrollRepo.save(payroll);
    }

    @PutMapping("/updatePayroll/{id}")
    public Payroll updatePayroll(@PathVariable Long id, @RequestBody Payroll payrollDetails) {
        Payroll payroll = payrollRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id " + id));

        payroll.setEmployee_id(payrollDetails.getEmployee_id());
        payroll.setMonth(payrollDetails.getMonth());
        payroll.setYear(payrollDetails.getYear());
        payroll.setSalary(payrollDetails.getSalary());
        payroll.setTaxes(payrollDetails.getTaxes());
        return payrollRepo.save(payroll);
    }


    @DeleteMapping("deletePayroll/{id}")
    public ResponseEntity<?> deletePayroll(@PathVariable Long id) {
        Payroll payroll = payrollRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id " + id));

        payrollRepo.delete(payroll);

        return ResponseEntity.ok().build();
    }


}