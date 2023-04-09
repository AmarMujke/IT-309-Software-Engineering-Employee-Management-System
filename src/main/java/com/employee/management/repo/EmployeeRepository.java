package com.employee.management.repo;

import com.employee.management.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    List<Employees> findByDepartmentId(int departmentId);

}
