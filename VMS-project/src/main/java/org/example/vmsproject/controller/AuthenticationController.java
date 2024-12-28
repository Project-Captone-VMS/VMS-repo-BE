package org.example.vmsproject.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.vmsproject.dto.request.AuthenticationRequest;
import org.example.vmsproject.dto.request.IntrospectRequest;
import org.example.vmsproject.dto.request.LogoutRequest;
import org.example.vmsproject.dto.request.RefreshRequest;
import org.example.vmsproject.dto.response.ApiResponse;
import org.example.vmsproject.dto.response.AuthenticationResponse;
import org.example.vmsproject.dto.response.IntrospectionResponse;
import org.example.vmsproject.service.impl.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "*",maxAge = 3600)
public class AuthenticationController {
    AuthenticationServiceImpl authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authentication(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectionResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectionResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse>authenticate(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refeshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result).build();
    }
}
