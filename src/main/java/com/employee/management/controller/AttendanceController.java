package com.employee.management.controller;

import com.employee.management.repo.AttendanceRepository;
import com.employee.management.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/getAll")
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Attendance getAttendanceById(@PathVariable(value = "id") Long id) {
        return attendanceRepository.getById(id);
    }

    @DeleteMapping("/deleteAttendance/{id}")
    public void deleteAttendance(@PathVariable(value = "id") Long id) {
        Attendance attendance = attendanceRepository.getById(id);
        attendanceRepository.delete(attendance);
    }
}