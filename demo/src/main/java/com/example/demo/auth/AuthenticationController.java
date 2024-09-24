package com.example.demo.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.request.RegisterRequest;
import com.example.demo.auth.request.SigninRequest;
import com.example.demo.auth.response.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "register") 
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping(path = "signin") 
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SigninRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.signin(registerRequest));
    }

}
