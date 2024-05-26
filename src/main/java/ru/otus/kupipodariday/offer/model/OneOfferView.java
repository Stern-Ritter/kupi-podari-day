package ru.otus.kupipodariday.offer.model;

import java.time.LocalDateTime;
import java.util.Set;


public interface OneOfferView {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    Double getAmount();
    Boolean getHidden();
    OneOfferUserView getUser();
    OneOfferWishView getWish();
    
    interface OneOfferUserView {
        Long getId();
        LocalDateTime getCreatedAt();
        LocalDateTime getUpdatedAt();
        String getUsername();
        String getAbout();
        String getAvatar();
        String getEmail();
        Set<OneOfferUserWishesView> getWishes();
        Set<OneOfferUserWishlistsView> getWishlists();
        Set<OneOfferUserOffersView> getOffers();
        
        interface OneOfferUserWishesView {
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
            OneOfferUserOwnerView getOwner();
            
            interface OneOfferUserOwnerView {
                Long getId();
                LocalDateTime getCreatedAt();
                LocalDateTime getUpdatedAt();
                String getUsername();
                String getAbout();
                String getAvatar();
                String getEmail();
            }
        }
        
        interface OneOfferUserWishlistsView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            String getName();
            String getDescription();
            String getImage();
            OneOfferUserWishlistsOwnerView getOwner();
            Set<OneOfferUserWishlistsWishesView> getWishes();
            
            interface OneOfferUserWishlistsOwnerView {
                Long getId();
                LocalDateTime getCreatedAt();
                LocalDateTime getUpdatedAt();
                String getUsername();
                String getAbout();
                String getAvatar();
                String getEmail();
            }
            
            interface OneOfferUserWishlistsWishesView {
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
        
        interface OneOfferUserOffersView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            Double getAmount();
            Boolean getHidden();
        }
    }
    
    interface OneOfferWishView {
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
        OneOfferWishOwnerView getOwner();
        Set<OneOfferWishOffersView> getOffers();

        interface OneOfferWishOwnerView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            String getUsername();
            String getAbout();
            String getAvatar();
            String getEmail();
        }
        
        interface OneOfferWishOffersView {
            Long getId();
            LocalDateTime getCreatedAt();
            LocalDateTime getUpdatedAt();
            Double getAmount();
            Boolean getHidden();
        }
    }
}