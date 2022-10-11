package com.example.adventurepark.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

@DataJpaTest
public class EmployeeServiceTest {

    public static EmployeeRepository staticRepository;
    public EmployeeService employeeService;

    static String username = "admin";
    static String password = "test";

    @BeforeAll
    public static void setupData(@Autowired EmployeeRepository userRepository) {
        staticRepository = userRepository;

        staticRepository.deleteAll();

        Employee admin = new Employee(username, password);
        
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
        EmployeeRequest employeeRequest = new EmployeeRequest(username, password);
        assertDoesNotThrow(() -> this.employeeService.signIn(employeeRequest));
    }

    @Test
    void signInInCorrect() {
        EmployeeRequest employeeRequest = new EmployeeRequest(username, password + "incorrect");

        assertThrows(ResponseStatusException.class, () -> {
            this.employeeService.signIn(employeeRequest);
        });
    }
}
