package com.employees.service;

import com.employees.dto.EmployeeDto;
import com.employees.model.Employee;
import com.employees.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public EmployeeDto findById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public EmployeeDto save(EmployeeDto employeeDto) {

        Employee employee = convertToEntity(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToDto(savedEmployee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<EmployeeDto> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<EmployeeDto> searchByEmail(String email) {
        return employeeRepository.searchByEmail(email)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private EmployeeDto convertToDto(Employee employee) {

        EmployeeDto dto = new EmployeeDto();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());

        return dto;
    }

    private Employee convertToEntity(EmployeeDto dto) {

        Employee employee = new Employee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        return employee;
    }
}
