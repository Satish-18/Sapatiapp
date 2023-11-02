package com.citytech.global.register.controller;
import com.citytech.global.common.utils.RestResponse;
import com.citytech.global.register.dto.login.LoginRequest;
import com.citytech.global.register.dto.login.LoginResponse;
import com.citytech.global.register.services.loginservices.AuthenticateService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;

import java.util.Optional;
@SerdeImport(LoginRequest.class)
@Controller("/paisade")
public class AuthenticationController {

    private AuthenticateService authenticateService;

    @Inject
    public AuthenticationController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @Post("/login")
    public HttpResponse<?> login(@Body LoginRequest loginRequest) {
        try{  Optional<LoginResponse> loginResponse = authenticateService.login(loginRequest);
            if (loginResponse.isPresent()) {
                return HttpResponse.ok(new RestResponse(200,"user is authenticated",null));
            } else {
                return HttpResponse.notFound(new RestResponse(400,"Authentication failed: User not found",null));
            }
        }catch(Exception e) {
            return HttpResponse.badRequest(new RestResponse(0,e.getMessage(),null));
        }
        }
    }
