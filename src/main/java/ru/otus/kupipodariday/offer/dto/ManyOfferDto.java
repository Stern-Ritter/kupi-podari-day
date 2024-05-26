package ru.otus.kupipodariday.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
public class ManyOfferDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double amount;
    private Boolean hidden;
    private ManyOfferOwnerViewDTO user;
    private ManyOfferWishViewDTO wish;

    @AllArgsConstructor
    @Getter
    public static class ManyOfferOwnerViewDTO {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String username;
        private String about;
        private String avatar;
        private String email;
        private Set<ManyOfferOwnerWishesViewDTO> wishes;
        private Set<ManyOfferOwnerWishlistsViewDTO> wishlists;
        private Set<ManyOfferOwnerOffersViewDTO> offers;

        @AllArgsConstructor
        @Getter
        public static class ManyOfferOwnerWishesViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private String name;
            private String link;
            private String image;
            private Double price;
            private Double raised;
            private String description;
            private Integer copied;
        }

        @AllArgsConstructor
        @Getter
        public static class ManyOfferOwnerWishlistsViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private String name;
            private String description;
            private String image;
        }

        @AllArgsConstructor
        @Getter
        public static class ManyOfferOwnerOffersViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private Double amount;
            private Boolean hidden;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class ManyOfferWishViewDTO {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String name;
        private String link;
        private String image;
        private Double price;
        private Double raised;
        private String description;
        private Integer copied;
        private ManyOfferWishOwnerViewDTO owner;
        private Set<ManyOfferWishOffersViewDTO> offers;

        @AllArgsConstructor
        @Getter
        public static class ManyOfferWishOwnerViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private String username;
            private String about;
            private String avatar;
            private String email;
        }

        @AllArgsConstructor
        @Getter
        public static class ManyOfferWishOffersViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private Double amount;
            private Boolean hidden;
        }
    }
}