package ru.otus.kupipodariday.validator.wishlist;

public class WishlistValidator {
    public static final String EMPTY_REQUEST_BODY_EXCEPTION = "Request body does not contain wishlist data";

    public static final String EMPTY_NAME_VALIDATION_EXCEPTION = "Name should not be empty.";
    public static final String EMPTY_IMAGE_VALIDATION_EXCEPTION = "Image should not be empty.";

    public static final String NAME_VALIDATION_EXCEPTION = "Name length should be between 1 and 250 characters.";
    public static final String IMAGE_VALIDATION_EXCEPTION = "Image should be valid url.";
    public static final String DESCRIPTION_VALIDATION_EXCEPTION = "Description length should be between 1 and 1500 characters.";
}
