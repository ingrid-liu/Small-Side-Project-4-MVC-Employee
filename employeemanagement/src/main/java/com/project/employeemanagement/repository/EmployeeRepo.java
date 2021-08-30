package com.project.employeemanagement.repository;

import com.project.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);    //query method
    Optional<Employee> findEmployeeById(long id);

}
