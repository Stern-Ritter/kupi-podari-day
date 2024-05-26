package ru.otus.kupipodariday.wish.model;

import java.time.LocalDateTime;
import java.util.Set;


public interface WishView {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getName();
    String getLink();
    String getImage();
    Double getPrice();
    Double getRaised();
    String getDescription();
    Integer getCopied();
    WishUserView getOwner();
    Set<WishOffersView> getOffers();

    interface WishUserView {
        Long getId();
        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();
        String getUsername();
        String getAbout();
        String getAvatar();
        String getEmail();
    }

    interface WishOffersView {
        Long getId();
        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();
        Double getAmount();
        Boolean getHidden();
        WishOffersUserView getUser();

        interface WishOffersUserView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            String getUsername();
            String getAbout();
            String getAvatar();
            String getEmail();
            Set<WishOffersUserWishesView> getWishes();
            Set<WishOffersUserWishlistsView> getWishlists();
            Set<WishOffersUserOffersView> getOffers();

            interface WishOffersUserWishesView {
                Long getId();
                LocalDateTime getCreatedAt();
                LocalDateTime getUpdatedAt();
                String getName();
                String getLink();
                String getImage();
                Double getPrice();
                Double getRaised();
                String getDescription();
                Integer getCopied();
            }

            interface WishOffersUserWishlistsView {
                Long getId();
                LocalDateTime getCreatedAt();
                LocalDateTime getUpdatedAt();
                String getName();
                String getDescription();
                String getImage();
                WishOffersUserWishlistsOwnerView getOwner();
                Set<WishOffersUserWishlistsWishesView> getWishes();

                interface WishOffersUserWishlistsOwnerView {
                    Long getId();
                    LocalDateTime getCreatedAt();
                    LocalDateTime getUpdatedAt();
                    String getUsername();
                    String getAbout();
                    String getAvatar();
                    String getEmail();
                }

                interface WishOffersUserWishlistsWishesView {
                    Long getId();
                    LocalDateTime getCreatedAt();
                    LocalDateTime getUpdatedAt();
                    String getName();
                    String getLink();
                    String getImage();
                    Double getPrice();
                    Double getRaised();
                    String getDescription();
                    Integer getCopied();
                }
            }

            interface WishOffersUserOffersView {
                Long getId();
                LocalDateTime getCreatedAt();
                LocalDateTime getUpdatedAt();
                Double getAmount();
                Boolean getHidden();
            }
        }
    }
}