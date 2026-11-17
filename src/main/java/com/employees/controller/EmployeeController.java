package com.employees.controller;

import com.employees.dto.EmployeeDto;
import com.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //This Annotation causes the output of the methods to be returned directly to the client in JSON format.
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public EmployeeDto save(@Valid @RequestBody EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/search")
    public List<EmployeeDto> findByFirstName(@RequestParam String firstName) {
        return employeeService.findByFirstName(firstName);
    }

    @GetMapping("/byDepartment")
    public List<EmployeeDto> findByDepartment(@RequestParam String department) {
        return employeeService.findByDepartment(department);
    }

    @GetMapping("/byEmail")
    public List<EmployeeDto> searchByEmail(@RequestParam String email) {
        return employeeService.searchByEmail(email);
    }
}
