package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.LoginResponse;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import com.period.myfreeperiod.data.model.User;
import com.period.myfreeperiod.data.repository.UserRepository;
import com.period.myfreeperiod.exceptions.BusinessLogicException;
import com.period.myfreeperiod.utils.UserLoginException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Builder
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        boolean emailExist = userRepository.findByEmail(request.getEmail()).isPresent();
        if (emailExist) throw new IllegalStateException("Email Already Exist");
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return RegisterResponse.builder()
                .message("Account created successfully")
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = (User) userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserLoginException("User does not exist"));
        LoginResponse response = new LoginResponse();
        if (user.getPassword().equals(request.getPassword())){
            response.setMessage("Login successful");
            response.setHttpStatus(HttpStatus.OK);
        }else {
            response.setMessage("re-login");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            throw new UserLoginException("wrong email or password");
        }
        return response;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->
                new BusinessLogicException(
                        String.format("User with id: %d not found", id)));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void changePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setPassword(newPassword);
        userRepository.save(user);
    }

    private static RegisterResponse getRegisterResponse(User savedUser) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(savedUser.getId());
        registerResponse.setSuccess(true);
        registerResponse.setMessage("User Registration Successful");
        return registerResponse;
    }
}
