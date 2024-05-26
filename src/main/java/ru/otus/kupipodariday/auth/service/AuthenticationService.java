package ru.otus.kupipodariday.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.kupipodariday.auth.dto.SignInRequest;
import ru.otus.kupipodariday.exceptions.UnauthorizedException;
import ru.otus.kupipodariday.user.dto.UpdateUserDto;
import ru.otus.kupipodariday.user.dto.UserDto;
import ru.otus.kupipodariday.user.mapper.UserMapper;
import ru.otus.kupipodariday.user.model.Role;
import ru.otus.kupipodariday.auth.dto.JwtAuthenticationResponse;
import ru.otus.kupipodariday.auth.dto.SignUpRequest;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.service.UserService;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;


    public UserDto signUp(SignUpRequest request) {
        User user = userMapper.updateModel(request, new User());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return userService.save(user);
    }

    public UserDto updateCurrentUser(UpdateUserDto updateUserDto) {
        User savedUser = userService.getCurrentUser();

        if (updateUserDto.getPassword() != null) {
            updateUserDto.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
        }

        User user = userMapper.updateModel(updateUserDto, savedUser);

        return userService.update(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
        } catch (InternalAuthenticationServiceException | BadCredentialsException e) {
            throw new UnauthorizedException("Некорректная пара логин и пароль");
        }

        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());
        String jwt = jwtService.generateToken(userDetails);

        return new JwtAuthenticationResponse(jwt);
    }
}
