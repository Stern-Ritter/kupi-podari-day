package ru.otus.kupipodariday.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String error;
}
