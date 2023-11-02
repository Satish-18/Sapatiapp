package com.citytech.global.register.services.loginservices;
import com.citytech.global.register.dto.login.LoginRequest;
import com.citytech.global.register.dto.login.LoginResponse;


import java.util.Optional;

public interface AuthenticateService {
    Optional<LoginResponse> login(LoginRequest loginRequest);
}
