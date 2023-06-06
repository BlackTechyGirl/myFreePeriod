package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.dto.requests.LoginRequest;
import com.period.myfreeperiod.data.dto.requests.RegisterRequest;
import com.period.myfreeperiod.data.dto.response.LoginResponse;
import com.period.myfreeperiod.data.dto.response.RegisterResponse;
import com.period.myfreeperiod.data.model.User;
import com.period.myfreeperiod.data.repository.UserRepository;
import com.period.myfreeperiod.utils.UserLoginException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


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
}
