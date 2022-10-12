package com.example.adventurepark.dto;

import com.example.adventurepark.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String username;

    private String userPassword;

    public static Employee getEmployeeEntity(EmployeeRequest employeeRequest) {
        return new Employee(employeeRequest.username, employeeRequest.userPassword);
    }
    
}
