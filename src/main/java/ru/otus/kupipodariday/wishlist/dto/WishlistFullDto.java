package ru.otus.kupipodariday.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class WishlistFullDto implements Serializable {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String description;
    private final String image;
    private final WishlistOwnerDto owner;
    @JsonProperty("items")
    private final List<WishlistWishesDto> wishes;

    @AllArgsConstructor
    @Getter
    public static class WishlistOwnerDto implements Serializable {
        private final Long id;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final String username;
        private final String about;
        private final String avatar;
        private final String email;
    }

    @AllArgsConstructor
    @Getter
    public static class WishlistWishesDto implements Serializable {
        private final Long id;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final String name;
        private final String link;
        private final String image;
        private final Double price;
        private final Double raised;
        private final String description;
        private final Integer copied;
    }
}