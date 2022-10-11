package com.example.adventurepark.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.adventurepark.dto.EmployeeRequest;
import com.example.adventurepark.entity.Employee;
import com.example.adventurepark.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void signIn(EmployeeRequest employeeSignIn) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByUsername(employeeSignIn.getUsername());

        if (employeeOptional.isPresent()) {
            Employee employeeFound = employeeOptional.get();

            if (BCrypt.checkpw(employeeSignIn.getUserPassword(), employeeFound.getUserPassword())) {

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find employee");
        }
    }

    private String genHash(String password) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(password, salt);
    }

}