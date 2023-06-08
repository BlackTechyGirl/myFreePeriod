package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.LoginResponse;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import com.period.myfreeperiod.data.model.User;

import java.util.List;

public interface UserService {

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

    User getUserById(Long id);
    List<User> getAllUsers();

    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void changePassword(Long id, String newPassword);
}
