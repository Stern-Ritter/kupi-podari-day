package ru.otus.kupipodariday.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.kupipodariday.auth.service.AuthenticationService;
import ru.otus.kupipodariday.user.dto.FindUsersRequest;
import ru.otus.kupipodariday.user.dto.UpdateUserDto;
import ru.otus.kupipodariday.user.dto.UserDto;
import ru.otus.kupipodariday.user.mapper.UserMapper;
import ru.otus.kupipodariday.user.service.UserService;
import ru.otus.kupipodariday.wish.dto.WishFullDto;
import ru.otus.kupipodariday.wish.service.WishService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final WishService wishService;
    private final AuthenticationService authenticationService;

    private UserMapper userMapper;

    @GetMapping("/me")
    public UserDto findCurrentUser() {
        return userService.findCurrentUser();
    }

    @PatchMapping("/me")
    public UserDto updateCurrentUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return authenticationService.updateCurrentUser(updateUserDto);
    }

    @GetMapping("/{username}")
    public UserDto findUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/me/wishes")
    public List<WishFullDto> findCurrentUserWishes() {
        return wishService.findCurrentUserWishes();
    }

    @GetMapping("/{username}/wishes")
    public List<WishFullDto> findUserWishes(@PathVariable String username) {
        return wishService.findUserWishes(username);
    }

    @PostMapping("/find")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserDto> findAllUsers(@Valid @RequestBody FindUsersRequest findUsersRequest) {
        String query = findUsersRequest.getQuery();
        return userService.findAllByUsernameOrEmail(query, query);
    }
}
