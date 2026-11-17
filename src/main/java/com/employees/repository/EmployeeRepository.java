package com.employees.repository;

import com.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /*
    Derived Query:
    Spring Data JPA understands from the method name that
    it should search for employees based on firstName.
    */

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByDepartment(String department);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    List<Employee> searchByEmail(@Param("email") String email);
}
