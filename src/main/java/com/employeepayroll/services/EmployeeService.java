package com.employeepayroll.services;

import com.employeepayroll.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    // In-memory list to store employees
    private final List<EmployeeDTO> employees = new ArrayList<>();

    // AtomicLong to generate unique IDs
    private final AtomicLong idCounter = new AtomicLong(1);

    // Add a new employee
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setId(idCounter.getAndIncrement()); // Assign a unique ID
        employees.add(employeeDTO); // Add to the list
        return employeeDTO;
    }

    // Get all employees
    public List<EmployeeDTO> getAllEmployees() {
        return employees;
    }

    // Get employee by ID
    public EmployeeDTO getEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Update an employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        EmployeeDTO existingEmployee = getEmployeeById(id); // Find the employee
        existingEmployee.setName(employeeDTO.getName()); // Update name
        existingEmployee.setSalary(employeeDTO.getSalary()); // Update salary
        return existingEmployee;
    }

    // Delete an employee
    public void deleteEmployee(Long id) {
        EmployeeDTO employeeToDelete = getEmployeeById(id); // Find the employee
        employees.remove(employeeToDelete); // Remove from the list
    }
}