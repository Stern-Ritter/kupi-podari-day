package ru.otus.kupipodariday.offer.model;

import java.time.LocalDateTime;
import java.util.Set;

public interface ManyOfferView {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    Double getAmount();
    Boolean getHidden();
    ManyOfferOwnerView getUser();
    ManyOfferWishView getWish();

    interface ManyOfferOwnerView {
        Long getId();
        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();
        String getUsername();
        String getAbout();
        String getAvatar();
        String getEmail();
        Set<ManyOfferOwnerWishesView> getWishes();
        Set<ManyOfferOwnerWishlistsView> getWishlists();
        Set<ManyOfferOwnerOffersView> getOffers();

        interface ManyOfferOwnerWishesView {
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

        interface ManyOfferOwnerWishlistsView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            String getName();
            String getDescription();
            String getImage();
        }

        interface ManyOfferOwnerOffersView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            Double getAmount();
            Boolean getHidden();
        }
    }

    interface ManyOfferWishView {
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
        ManyOfferWishOwnerView getOwner();
        Set<ManyOfferWishOffersView> getOffers();

        interface ManyOfferWishOwnerView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            String getUsername();
            String getAbout();
            String getAvatar();
            String getEmail();
        }

        interface ManyOfferWishOffersView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            Double getAmount();
            Boolean getHidden();
        }
    }
}