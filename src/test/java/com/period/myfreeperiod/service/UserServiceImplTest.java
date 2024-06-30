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
        loginRequest.setEmail("test@email.com");
        loginRequest.setPassword("testPassword");

        user = new User();
        user.setFirstName("existingFirstName");
        user.setLastName("existingLastName");
        user.setEmail("existing@email.com");
        user.setPassword("existingPassword");
        user = userRepository.save(user);
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
    void loginWithCorrectCredentials() {
        LoginResponse loginResponse = userService.login(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertEquals("Login successful", loginResponse.getMessage());
        assertEquals(HttpStatus.OK, loginResponse.getHttpStatus());
    }

    @Test
    void loginWithIncorrectPassword() {
        LoginRequest wrongPasswordRequest = new LoginRequest();
        wrongPasswordRequest.setEmail("test@email.com");
        wrongPasswordRequest.setPassword("wrongPassword");

        UserLoginException exception = assertThrows(UserLoginException.class, () -> {
            userService.login(wrongPasswordRequest);
        });

        assertEquals("wrong email or password", exception.getMessage());
    }

    @Test
    void getUserById() {
        User foundUser = userService.getUserById(user.getId());
        assertThat(foundUser).isNotNull();
        assertEquals(user.getFirstName(), foundUser.getFirstName());
    }

    @Test
    void getAllUsers() {
        List<User> users = userService.getAllUsers();
        assertThat(users).isNotEmpty();
    }

    @Test
    void updateUser() {
        user.setFirstName("updatedFirstName");
        User updatedUser = userService.updateUser(user.getId(), user);
        assertThat(updatedUser).isNotNull();
        assertEquals("updatedFirstName", updatedUser.getFirstName());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(user.getId());
        assertThrows(BusinessLogicException.class, () -> {
            userService.getUserById(user.getId());
        });
    }

    @Test
    void changePassword() {
        String newPassword = "newPassword";
        userService.changePassword(user.getId(), newPassword);
        User updatedUser = userService.getUserById(user.getId());
        assertEquals(newPassword, updatedUser.getPassword());
    }

}


