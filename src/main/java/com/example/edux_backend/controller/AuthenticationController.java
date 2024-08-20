package com.example.edux_backend.controller;

import com.example.edux_backend.dto.request.AuthenticationRequest;
import com.example.edux_backend.dto.request.IntrospectRequest;
import com.example.edux_backend.dto.response.ApiResponse;
import com.example.edux_backend.dto.response.AuthenticationResponse;
import com.example.edux_backend.dto.response.IntrospectResponse;
import com.example.edux_backend.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:8088")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws JOSEException {
       var result = authenticationService.authenticate(request);
       return ApiResponse.<AuthenticationResponse>builder()
               .result(result)
               .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}
