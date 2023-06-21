package com.employee.management.controller;

import com.employee.management.repo.AttendanceRepository;
import com.employee.management.model.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping("/addAttendance")
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @GetMapping("/getByEmployeeId/{employee_id}")
    public List<Attendance> getAttendanceByEmployeeId(@PathVariable(value = "employee_id") Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    @PutMapping("/updateStatus/{employeeId}")
    public ResponseEntity<String> updateAttendanceStatus(@PathVariable(value = "employeeId") Long employeeId, @RequestBody String newStatus) {
        List<Attendance> attendanceList = attendanceRepository.findByEmployeeId(employeeId);

        if (!attendanceList.isEmpty()) {
            attendanceList.forEach(attendance -> {
                attendance.setStatus(newStatus);
                attendanceRepository.save(attendance);
            });

            return ResponseEntity.ok("Status updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateTimeOut/{employeeId}")
    public ResponseEntity<String> updateAttendanceTimeOut(@PathVariable(value = "employeeId") Long employeeId) {
        List<Attendance> attendanceList = attendanceRepository.findByEmployeeId(employeeId);
        int lastIndex = attendanceList.size() - 1;

        if (lastIndex >= 0) {
            Attendance lastAttendance = attendanceList.get(lastIndex);
            LocalDateTime currentTime = LocalDateTime.now();
            lastAttendance.setTime_out(currentTime.toLocalTime());
            attendanceRepository.save(lastAttendance);
            return ResponseEntity.ok("Time Out updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}