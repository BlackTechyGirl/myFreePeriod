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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

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

    @Test
    void testRegisterEmailAlreadyExistsExceptionThrown() {
        User existingUser = new User();
        userRepository.save(existingUser);

        assertThrows(IllegalStateException.class, () -> userService.register(registerRequest));
    }

    @Test
    void testLogin_UserExistsAndPasswordMatches_Success() {
        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());
        userRepository.save(user);

        LoginResponse response = userService.login(loginRequest);

        assertEquals("Login successful", response.getMessage());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    void testLogin_UserDoesNotExist_ExceptionThrown() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("john.doe@example.com");

        // Act and Assert
        assertThrows(UserLoginException.class, () -> userService.login(request));
    }

    @Test
    void testLogin_UserExistsButPasswordDoesNotMatch_ExceptionThrown() {
        // Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("incorrect");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("password");
        userRepository.save(user);

        // Act and Assert
        assertThrows(UserLoginException.class, () -> userService.login(request));
    }

    @Test
    void testGetUserById_UserExists_Success() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        userRepository.save(user);

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void testGetUserById_UserDoesNotExist_ExceptionThrown() {
        // Arrange
        Long userId = 1L;

        // Act and Assert
        assertThrows(BusinessLogicException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        List<User> userList = userService.getAllUsers();

        // Assert
        assertEquals(2, userList.size());
        assertTrue(userList.contains(user1));
        assertTrue(userList.contains(user2));
    }

    // Tests for saveUser, updateUser, deleteUser, and changePassword methods can be written in a similar manner.
}


