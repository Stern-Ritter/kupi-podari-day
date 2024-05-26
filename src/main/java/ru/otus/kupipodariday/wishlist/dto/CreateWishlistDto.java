package ru.otus.kupipodariday.wishlist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

import static ru.otus.kupipodariday.validator.wishlist.WishlistValidator.DESCRIPTION_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wishlist.WishlistValidator.EMPTY_NAME_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wishlist.WishlistValidator.EMPTY_REQUEST_BODY_EXCEPTION;
import static ru.otus.kupipodariday.validator.wishlist.WishlistValidator.IMAGE_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.wishlist.WishlistValidator.NAME_VALIDATION_EXCEPTION;

@Data
@NotNull(message = EMPTY_REQUEST_BODY_EXCEPTION)
public class CreateWishlistDto {
    @NotNull(message = EMPTY_NAME_VALIDATION_EXCEPTION)
    @Length(min = 1, max = 250, message = NAME_VALIDATION_EXCEPTION)
    private String name;

    @Length(min = 1, max = 1500, message = DESCRIPTION_VALIDATION_EXCEPTION)
    private String description;

    @URL(message = IMAGE_VALIDATION_EXCEPTION)
    private String image;

    private List<Long> itemsId;
}
