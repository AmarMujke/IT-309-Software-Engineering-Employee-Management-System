package com.employee.management.repo;

import com.employee.management.model.EmployeePasswords;
import io.qameta.allure.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePasswordRepository extends JpaRepository<EmployeePasswords, Long> {

    @Modifying
    @Query(value = "INSERT INTO employee_passwords (employee_id, password) VALUES (:employeeId, :password)", nativeQuery = true)
    void savePassword(@Param("employeeId") Long employeeId, @Param("password") String password);

    @Modifying
    @Query(value = "DELETE FROM employee_passwords WHERE employee_id = :employeeId", nativeQuery = true)
    void deleteByEmployeeId(@Param("employeeId") Long employeeId);

}
