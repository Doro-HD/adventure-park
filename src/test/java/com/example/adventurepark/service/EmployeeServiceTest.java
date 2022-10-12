package com.example.adventurepark.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.server.ResponseStatusException;

import com.example.adventurepark.dto.EmployeeRequest;
import com.example.adventurepark.entity.Employee;
import com.example.adventurepark.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeServiceTest {

    public static EmployeeRepository staticRepository;
    public EmployeeService employeeService;

    static String correctUsername = "admin";
    static String correctPassword = "test";

    @BeforeAll
    public static void setupData(@Autowired EmployeeRepository userRepository) {
        staticRepository = userRepository;

        staticRepository.deleteAll();

        Employee admin = new Employee(correctUsername, correctPassword);
        
        String salt = BCrypt.gensalt(12);
        String hash = BCrypt.hashpw(admin.getUserPassword(), salt);

        admin.setUserPassword(hash);

        staticRepository.save(admin);
    }

    @BeforeEach
    public void initService() {
        this.employeeService = new EmployeeService(staticRepository);
    }

    @Test
    void signInCorrect() {
        EmployeeRequest employeeRequest = new EmployeeRequest(correctUsername, correctPassword);

        //Will throw http 400 or 500 if information is incorrect
        //Will throw JWTDecodeException if token is invalid
        assertDoesNotThrow(
                () -> {
                    String jwt = this.employeeService.signIn(employeeRequest);

                    JWTHandler jwtHandler = new JWTHandler();
                    jwtHandler.decode(jwt);

                    assertEquals(jwtHandler.getUsername(), correctUsername);

                }
        );
    }

    @Test
    void signInInCorrect() {
        EmployeeRequest employeeRequest = new EmployeeRequest(correctUsername, correctPassword + "incorrect");

        assertThrows(ResponseStatusException.class, () -> {
            this.employeeService.signIn(employeeRequest);
        });
    }
}
