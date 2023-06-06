package com.period.myfreeperiod.service;


import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.LoginResponse;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private  UserService userService;
    private RegisterRequest request;
    private LoginRequest loginRequest;


    @BeforeEach
    void setUp(){
        request = new RegisterRequest();
        request.setFirstName("testFirstName");
        request.setLastName("testLastName");
        request.setEmail("test@email.com");
        request.setPassword("testPassword");

    }

    @Test
    void registerTest() {
        RegisterResponse registerResponse = userService.register(request);
        assertThat(registerResponse).isNotNull();
    }



}
