package ru.otus.kupipodariday.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static ru.otus.kupipodariday.validator.user.UserValidator.ABOUT_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.AVATAR_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.EMAIL_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.EMPTY_REQUEST_BODY_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.PASSWORD_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.USERNAME_VALIDATION_EXCEPTION;

@Data
@NotNull(message = EMPTY_REQUEST_BODY_EXCEPTION)
public class UpdateUserDto {
    @Length(min = 5, max = 30, message = USERNAME_VALIDATION_EXCEPTION)
    private String username;

    @Length(max = 200, message = ABOUT_VALIDATION_EXCEPTION)
    private String about;

    @URL(message = AVATAR_VALIDATION_EXCEPTION)
    @Length(max = 1024, message = AVATAR_VALIDATION_EXCEPTION)
    private String avatar;

    @Email(message = EMAIL_VALIDATION_EXCEPTION)
    @Length(min = 5, max = 256, message = EMAIL_VALIDATION_EXCEPTION)
    private String email;

    @Length(min = 8, max = 256, message = PASSWORD_VALIDATION_EXCEPTION)
    private String password;
}
