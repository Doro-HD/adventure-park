package com.example.adventurepark.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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

    public String signIn(EmployeeRequest employeeSignIn) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByUsername(employeeSignIn.getUsername());

        if (employeeOptional.isPresent()) {
            Employee employeeFound = employeeOptional.get();

            if (BCrypt.checkpw(employeeSignIn.getUserPassword(), employeeFound.getUserPassword())) {
                JWTHandler jwt = new JWTHandler();
                jwt.sign(employeeFound.getUsername());

                return jwt.getAccessToken();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find employee");
        }
    }
}