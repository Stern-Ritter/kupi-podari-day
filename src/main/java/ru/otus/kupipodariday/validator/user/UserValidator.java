package ru.otus.kupipodariday.validator.user;

public class UserValidator {
    public static final String EMPTY_REQUEST_BODY_EXCEPTION = "Request body does not contain user data.";
    public static final String EMPTY_FIND_REQUEST_BODY_EXCEPTION = "Find request body does not contain query.";

    public static final String EMPTY_USERNAME_VALIDATION_EXCEPTION = "Username should not be empty.";
    public static final String EMPTY_PASSWORD_VALIDATION_EXCEPTION = "Password should not be empty.";
    public static final String EMPTY_EMAIL_VALIDATION_EXCEPTION = "Email should not be empty.";
    public static final String EMPTY_QUERY_VALIDATION_EXCEPTION = "Query should not be empty.";

    public static final String USERNAME_VALIDATION_EXCEPTION = "Username length should be between 5 and 30 characters.";
    public static final String ABOUT_VALIDATION_EXCEPTION = "About length should not be more than 200 characters.";
    public static final String AVATAR_VALIDATION_EXCEPTION = "Avatar should be valid url with length not more than 1024 characters.";
    public static final String EMAIL_VALIDATION_EXCEPTION = "Email should be valid email address with length between 5 and 256 characters.";
    public static final String PASSWORD_VALIDATION_EXCEPTION = "Password length should be between 8 and 256 characters.";
}
