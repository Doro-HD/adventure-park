package com.example.adventurepark.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurepark.dto.EmployeeRequest;
import com.example.adventurepark.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @PostMapping()
    public ResponseEntity<String> signIn(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.signIn(employeeRequest);
        
        return new ResponseEntity<>("Sign-In successful", HttpStatus.OK);
    }

}
