package ru.otus.kupipodariday.wishlist.model;

import java.time.LocalDateTime;
import java.util.List;

public interface WishlistView {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    String getName();
    String getDescription();
    String getImage();
    WishlistUserView getOwner();
    List<WishlistWishesView> getWishes();

    interface WishlistUserView {
        Long getId();
        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();
        String getUsername();
        String getAbout();
        String getAvatar();
        String getEmail();
    }

    interface WishlistWishesView {
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