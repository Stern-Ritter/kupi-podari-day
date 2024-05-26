package ru.otus.kupipodariday.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static ru.otus.kupipodariday.validator.user.UserValidator.EMPTY_PASSWORD_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.EMPTY_REQUEST_BODY_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.EMPTY_USERNAME_VALIDATION_EXCEPTION;

@Data
@NotNull(message = EMPTY_REQUEST_BODY_EXCEPTION)
public class SignInRequest {
    @NotEmpty(message = EMPTY_USERNAME_VALIDATION_EXCEPTION)
    private String username;

    @NotEmpty(message = EMPTY_PASSWORD_VALIDATION_EXCEPTION)
    private String password;
}
