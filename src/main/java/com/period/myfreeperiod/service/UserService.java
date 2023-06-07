package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.LoginResponse;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import com.period.myfreeperiod.data.model.User;
import org.springframework.data.web.JsonPath;

import java.util.List;

public interface UserService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();

    User saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    void changePassword(Long id, String newPassword);
}
