package com.employee.management.repo;

import com.employee.management.model.Employees;
import io.qameta.allure.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByDepartmentId(int departmentId);
    Employees findByEmail(String email);

    List<Employees> findByName(String name);


    @Query("SELECT CASE WHEN COUNT(ep) > 0 THEN true ELSE false END FROM EmployeePasswords ep WHERE ep.employeeId = :employeeId AND ep.password = :password")
    boolean checkPassword(@Param("employeeId") Long employeeId, @Param("password") String password);

}
