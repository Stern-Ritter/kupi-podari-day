package ru.otus.kupipodariday.wish.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import static ru.otus.kupipodariday.validator.wish.WishValidator.DESCRIPTION_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wish.WishValidator.EMPTY_NAME_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wish.WishValidator.EMPTY_REQUEST_BODY_EXCEPTION;
import static ru.otus.kupipodariday.validator.wish.WishValidator.IMAGE_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wish.WishValidator.LINK_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wish.WishValidator.NAME_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wish.WishValidator.PRICE_VALIDATION_EXCEPTION;

@Data
@NotNull(message = EMPTY_REQUEST_BODY_EXCEPTION)
public class CreateWishDto {
    @NotNull(message = EMPTY_NAME_VALIDATION_EXCEPTION)
    @Length(min = 1, max = 250, message = NAME_VALIDATION_EXCEPTION)
    private String name;

    @URL(message = LINK_VALIDATION_EXCEPTION)
    private String link;

    @URL(message = IMAGE_VALIDATION_EXCEPTION)
    private String image;

    @Min(value = 1, message = PRICE_VALIDATION_EXCEPTION)
    private Double price;

    @Length(min = 1, max = 1024, message = DESCRIPTION_VALIDATION_EXCEPTION)
    private String description;
}
