package com.softhaxi.marves.core.repository.employee;

import java.util.Optional;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;
import com.softhaxi.marves.core.domain.employee.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raja Sihombing
 * @since 1
 */
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    
    @Query("FROM Employee WHERE user = ?1")
    Optional<Employee> findEmployeeByUserName(User user);
}
