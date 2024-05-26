package ru.otus.kupipodariday.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
public class WishFullDto {
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
    private WishUserViewDTO owner;
    private Set<WishOffersViewDTO> offers;

    @AllArgsConstructor
    @Getter
    public static class WishUserViewDTO {
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
    public static class WishOffersViewDTO {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Double amount;
        private Boolean hidden;
        private WishOffersUserViewDTO user;

        @AllArgsConstructor
        @Getter
        public static class WishOffersUserViewDTO {
            private Long id;
            private LocalDateTime createdAt;
            private LocalDateTime updatedAt;
            private String username;
            private String about;
            private String avatar;
            private String email;
            private Set<WishOffersUserWishesViewDTO> wishes;
            private Set<WishOffersUserWishlistsViewDTO> wishlists;
            private Set<WishOffersUserOffersViewDTO> offers;

            @AllArgsConstructor
            @Getter
            public static class WishOffersUserWishesViewDTO {
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
            public static class WishOffersUserWishlistsViewDTO {
                private Long id;
                private LocalDateTime createdAt;
                private LocalDateTime updatedAt;
                private String name;
                private String description;
                private String image;
                private WishOffersUserWishlistsOwnerViewDTO owner;
                private Set<WishOffersUserWishlistsWishesViewDTO> wishes;

                @AllArgsConstructor
                @Getter
                public static class WishOffersUserWishlistsOwnerViewDTO {
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
                public static class WishOffersUserWishlistsWishesViewDTO {
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
            public static class WishOffersUserOffersViewDTO {
                private Long id;
                private LocalDateTime createdAt;
                private LocalDateTime updatedAt;
                private Double amount;
                private Boolean hidden;
            }
        }
    }
}
