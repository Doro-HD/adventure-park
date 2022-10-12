package com.example.adventurepark.service;

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
                String jwt;
                try {
                    Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
                    jwt = JWT.create()
                            .withIssuer(System.getenv("jwt_issuer"))
                            .withClaim("username", employeeFound.getUsername())
                            .sign(algorithm);
                } catch (JWTCreationException exception){
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "jwt configuration error");
                }

                return jwt;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find employee");
        }
    }
}