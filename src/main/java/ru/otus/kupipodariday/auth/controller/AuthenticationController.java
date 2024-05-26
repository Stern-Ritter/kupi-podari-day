package ru.otus.kupipodariday.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.kupipodariday.auth.service.AuthenticationService;
import ru.otus.kupipodariday.auth.dto.SignUpRequest;
import ru.otus.kupipodariday.auth.dto.SignInRequest;
import ru.otus.kupipodariday.auth.dto.JwtAuthenticationResponse;
import ru.otus.kupipodariday.user.dto.UserDto;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto signUp(@Valid @RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse signIn(@Valid @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
