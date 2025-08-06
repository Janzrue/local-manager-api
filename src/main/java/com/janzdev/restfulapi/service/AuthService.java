package com.janzdev.restfulapi.service;

import com.janzdev.restfulapi.controller.models.AuthResponse;
import com.janzdev.restfulapi.controller.models.AuthenticationRequest;
import com.janzdev.restfulapi.controller.models.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthenticationRequest request);
}
