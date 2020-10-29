package com.softhaxi.marves.core.repository.employee;

import java.util.UUID;

import com.softhaxi.marves.core.domain.employee.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    
}
