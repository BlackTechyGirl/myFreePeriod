package com.period.myfreeperiod.service;


import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import com.period.myfreeperiod.data.model.User;
import com.period.myfreeperiod.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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

    private User user = new User();


    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setFirstName("testFirstName");
        registerRequest.setLastName("testLastName");
        registerRequest.setEmail("test@email.com");
        registerRequest.setPassword("testPassword");

    }

    @Test
    void registerTest() {
        RegisterResponse registerResponse = userService.register(registerRequest);
        assertThat(registerResponse).isNotNull();
        assertTrue(registerResponse.isSuccess());
        assertEquals("Account created successfully", registerResponse.getMessage());
        assertEquals(HttpStatus.CREATED, registerResponse.getStatus());
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        userRepository.save(user);

        assertThrows(IllegalStateException.class, () -> userService.register(registerRequest));
    }
//
//    @Test
//    void testLogin_SuccessfulLogin() {
//        // Arrange
//        String email = "john@example.com";
//        String password = "password";
//        userRepository.save(new User("John", "Doe", email, password));
//        LoginRequest request = new LoginRequest(email, password);
//
//        // Act
//        LoginResponse response = userService.login(request);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals("Login successful", response.getMessage());
//        assertEquals(HttpStatus.OK, response.getHttpStatus());
//    }
//
//    @Test
//    void testLogin_UserDoesNotExist() {
//        // Arrange
//        String email = "john@example.com";
//        String password = "password";
//        LoginRequest request = new LoginRequest(email, password);
//
//        // Act & Assert
//        assertThrows(UserLoginException.class, () -> userService.login(request));
//    }
//
//    // Additional tests for other methods can be written in a similar manner.


}


