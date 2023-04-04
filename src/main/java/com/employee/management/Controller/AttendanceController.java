package com.employee.management.Controller;

import com.employee.management.Repository.AttendanceRepository;
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
    public List<Attendance> getAllAttendance(){
        return attendanceRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Attendance getAttendanceById(@PathVariable(value = "id") Long id){
        return attendanceRepository.getById(id);
     }
  @GetMapping("/getAttendanceByEmployee/{employee_id}")
   public List<Attendance> getAttendanceByEmployee(@PathVariable int employee_id){
    return attendanceRepository.findByEmployeeId(employee_id);}


    @DeleteMapping("/deleteAttendance/{id}")
    public void deleteAttendance(@PathVariable(value = "id") Long id){
        Attendance attendance = attendanceRepository.getById(id);
        attendanceRepository.delete(attendance);
    }
}
