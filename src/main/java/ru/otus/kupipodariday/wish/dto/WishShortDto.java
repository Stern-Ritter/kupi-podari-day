package ru.otus.kupipodariday.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class WishShortDto implements Serializable {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String name;
    private final String link;
    private final String image;
    private final Double price;
    private final Double raised;
    private final Integer copied;
    private final String description;
}