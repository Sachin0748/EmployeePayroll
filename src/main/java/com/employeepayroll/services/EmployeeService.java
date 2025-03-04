package com.employeepayroll.services;

import com.employeepayroll.dto.EmployeeDTO;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j // Lombok annotation for logging
public class EmployeeService {

    private final List<EmployeeDTO> employees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Add a new employee
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding a new employee: {}", employeeDTO); // Log the action
        employeeDTO.setId(idCounter.getAndIncrement()); // Assign a unique ID
        employees.add(employeeDTO); // Add to the list
        return employeeDTO;
    }

    // Get all employees
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees"); // Log the action
        return employees;
    }

    // Get employee by ID
    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id); // Log the action
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Employee not found with ID: {}", id); // Log the error
                    return new RuntimeException("Employee not found");
                });
    }

    // Update an employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id); // Log the action
        EmployeeDTO existingEmployee = getEmployeeById(id); // Find the employee
        existingEmployee.setName(employeeDTO.getName()); // Update name
        existingEmployee.setSalary(employeeDTO.getSalary()); // Update salary
        return existingEmployee;
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id); // Log the action
        EmployeeDTO employeeToDelete = getEmployeeById(id); // Find the employee
        employees.remove(employeeToDelete); // Remove from the list
    }
}