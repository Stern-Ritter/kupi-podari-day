package ru.otus.kupipodariday.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kupipodariday.exceptions.NotFoundException;
import ru.otus.kupipodariday.exceptions.UserAlreadyExistsException;
import ru.otus.kupipodariday.user.dto.UserDto;
import ru.otus.kupipodariday.user.mapper.UserMapper;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.model.UserView;
import ru.otus.kupipodariday.user.storage.UserRepository;
import ru.otus.kupipodariday.utils.Utils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<UserDto> findAllByUsernameOrEmail(String username, String email) {
        List<UserView> users = userRepository.
                findAllByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(username, email);

        return userMapper.toDto(users);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public UserDto findCurrentUser() {
        User user = getCurrentUser();

        return userMapper.toDto(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public UserDto findByUsername(String username) {
        UserView user = userRepository.findByUsername(username).
                orElseThrow(() -> new NotFoundException(String.format("Пользователь с логином: %s не найден", username)));

        return userMapper.toDto(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDto save(User user) {
        try {
            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        } catch (DataIntegrityViolationException e) {
            if (Utils.isUniqueConstraintViolation(e, "users_username_unique")) {
                throw new UserAlreadyExistsException("Пользователь с таким логином уже зарегистрирован");
            }
            if (Utils.isUniqueConstraintViolation(e, "users_email_unique")) {
                throw new UserAlreadyExistsException("Пользователь с таким email уже зарегистрирован");
            }
            throw new UserAlreadyExistsException("Пользователь уже существует");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDto update(User user) {
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public User getByUsername(String username) {
        return userRepository.getByUsername(username).
                orElseThrow(() -> new NotFoundException(String.format("Пользователь с логином: %s не найден", username)));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return getByUsername(username);
    }
}
