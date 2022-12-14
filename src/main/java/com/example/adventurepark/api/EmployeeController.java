package com.example.adventurepark.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurepark.dto.EmployeeRequest;
import com.example.adventurepark.service.EmployeeService;
import com.example.adventurepark.service.JWTHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("api/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> signIn(@RequestBody EmployeeRequest employeeRequest, HttpServletResponse response) {
        String jwt = employeeService.signIn(employeeRequest);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);

        return new ResponseEntity<>("Successful sign in", HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity<String> isAuthorized(@CookieValue("jwt") String jwtAccessToken) {
        JWTHandler jwtHandler = new JWTHandler();
        jwtHandler.decode(jwtAccessToken);

        return new ResponseEntity<>("User Authorized" ,HttpStatus.OK);
    }
}


