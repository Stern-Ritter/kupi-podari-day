package ru.otus.kupipodariday.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
public class OneOfferDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double amount;
    private Boolean hidden;
    private OneOfferUserViewDTO user;
    private OneOfferWishViewDTO wish;

    @AllArgsConstructor
    @Getter
    public static class OneOfferUserViewDTO {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String username;
        private String about;
        private String avatar;
        private String email;
        private Set<OneOfferUserWishesViewDTO> wishes;
        private Set<OneOfferUserWishlistsViewDTO> wishlists;
        private Set<OneOfferUserOffersViewDTO> offers;

        @AllArgsConstructor
        @Getter
        public static class OneOfferUserWishesViewDTO {
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
            private OneOfferUserOwnerViewDTO owner;

            @AllArgsConstructor
            @Getter
            public static class OneOfferUserOwnerViewDTO {
                private Long id;
                private LocalDateTime createdAt;
                private LocalDateTime updatedAt;
                private String username;
                private String about;
                private String avatar;
                private String email;
            }
        }

        @AllArgsConstructor
        @Getter
        public static class OneOfferUserWishlistsViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private String name;
            private String description;
            private String image;
            private OneOfferUserWishlistsOwnerViewDTO owner;
            private Set<OneOfferUserWishlistsWishesViewDTO> wishes;

            @AllArgsConstructor
            @Getter
            public static class OneOfferUserWishlistsOwnerViewDTO {
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
            public static class OneOfferUserWishlistsWishesViewDTO {
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
        }

        @AllArgsConstructor
        @Getter
        public static class OneOfferUserOffersViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private Double amount;
            private Boolean hidden;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class OneOfferWishViewDTO {
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
        private OneOfferWishOwnerViewDTO owner;
        private Set<OneOfferWishOffersViewDTO> offers;

        @AllArgsConstructor
        @Getter
        public static class OneOfferWishOwnerViewDTO {
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
        public static class OneOfferWishOffersViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private Double amount;
            private Boolean hidden;
        }
    }
}