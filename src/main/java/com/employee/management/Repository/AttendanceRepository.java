package com.employee.management.Repository;

import com.employee.management.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    //all crud database methods
   List<Attendance>findByEmployeeId(int employee_id);
}
