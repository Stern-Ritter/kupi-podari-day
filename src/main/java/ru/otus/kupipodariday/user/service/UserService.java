package ru.otus.kupipodariday.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.kupipodariday.user.dto.UserDto;
import ru.otus.kupipodariday.user.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAllByUsernameOrEmail(String username, String email);

    UserDto findCurrentUser();

    UserDto findByUsername(String username);

    UserDto save(User user);

    UserDto update(User user);

    User getByUsername(String username);

    UserDetailsService userDetailsService();

    User getCurrentUser();
}
