package com.employeepayroll.dto;

public class EmployeeDTO {
    private Long id;
    private String name;
    private Double salary;

    // Default constructor
    public EmployeeDTO() {}

    // Parameterized constructor
    public EmployeeDTO(Long id , String name, Double salary) {
        this.id=id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}