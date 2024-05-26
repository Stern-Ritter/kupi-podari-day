package ru.otus.kupipodariday.utils;

import org.springframework.dao.DataIntegrityViolationException;

public class Utils {
    public static boolean isUniqueConstraintViolation(DataIntegrityViolationException e, String constraintName) {
        Throwable rootCause = e.getRootCause();
        if (rootCause != null && rootCause.getMessage() != null) {
            return rootCause.getMessage().contains(constraintName);
        }
        return false;
    }
}
