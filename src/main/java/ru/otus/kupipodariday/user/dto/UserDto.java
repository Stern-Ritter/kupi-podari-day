package ru.otus.kupipodariday.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto implements Serializable {
    private final Long id;
    private final String username;
    private final String about;
    private final String avatar;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}