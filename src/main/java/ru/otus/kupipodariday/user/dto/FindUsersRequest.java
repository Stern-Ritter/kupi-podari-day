package ru.otus.kupipodariday.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static ru.otus.kupipodariday.validator.user.UserValidator.EMPTY_FIND_REQUEST_BODY_EXCEPTION;
import static ru.otus.kupipodariday.validator.user.UserValidator.EMPTY_QUERY_VALIDATION_EXCEPTION;

@Data
@NotNull(message = EMPTY_FIND_REQUEST_BODY_EXCEPTION)
public class FindUsersRequest {
    @NotEmpty(message = EMPTY_QUERY_VALIDATION_EXCEPTION)
    private String query;
}
