package ru.otus.kupipodariday.offer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static ru.otus.kupipodariday.validator.offer.OfferValidator.AMOUNT_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.offer.OfferValidator.EMPTY_ITEM_ID_VALIDATION_EXCEPTION;
import static ru.otus.kupipodariday.validator.offer.OfferValidator.EMPTY_REQUEST_BODY_EXCEPTION;

@Data
@NotNull(message = EMPTY_REQUEST_BODY_EXCEPTION)
public class CreateOfferDto {
    @Min(value = 1, message = AMOUNT_VALIDATION_EXCEPTION)
    private Double amount;

    private Boolean hidden;

    @NotNull(message = EMPTY_ITEM_ID_VALIDATION_EXCEPTION)
    private Long itemId;
}
