package ru.otus.kupipodariday.validator.wish;

public class WishValidator {
    public static final String EMPTY_REQUEST_BODY_EXCEPTION = "Request body does not contain wish data";

    public static final String EMPTY_NAME_VALIDATION_EXCEPTION = "Name should not be empty.";

    public static final String NAME_VALIDATION_EXCEPTION = "Name length should be between 1 and 250 characters.";
    public static final String LINK_VALIDATION_EXCEPTION = "Link should be valid url.";
    public static final String IMAGE_VALIDATION_EXCEPTION = "Image should be valid url.";
    public static final String PRICE_VALIDATION_EXCEPTION = "Price should be greater than 0.";
    public static final String DESCRIPTION_VALIDATION_EXCEPTION = "Description length should be between 1 and 1024 characters.";
}
