package com.employee.management.repo;

import com.employee.management.model.Attendance;
import io.qameta.allure.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    @Query("SELECT a FROM Attendance a WHERE a.employee_id = :employeeId")
    List<Attendance> findByEmployeeId(@Param("employeeId") Long employeeId);

}
