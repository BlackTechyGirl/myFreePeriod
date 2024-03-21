package com.period.myfreeperiod.service;


import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.LoginResponse;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import com.period.myfreeperiod.data.model.User;
import com.period.myfreeperiod.data.repository.UserRepository;
import com.period.myfreeperiod.exceptions.BusinessLogicException;
import com.period.myfreeperiod.utils.UserLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;

    private User user;


    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("testFirstName");
        registerRequest.setLastName("testLastName");
        registerRequest.setEmail("test@email.com");
        registerRequest.setPassword("testPassword");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("john.doe@example.com");
        loginRequest.setPassword("password");

        user = new User();
    }

    @Test
    void registerTest() {
        RegisterResponse registerResponse = userService.register(registerRequest);
        assertThat(registerResponse).isNotNull();
        assertTrue(registerResponse.isSuccess());
        assertEquals("Account created successfully", registerResponse.getMessage());
        assertEquals(HttpStatus.CREATED, registerResponse.getStatus());
}

}


