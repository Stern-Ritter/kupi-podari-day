package ru.otus.kupipodariday.wishlist.service;

import ru.otus.kupipodariday.wishlist.dto.CreateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.UpdateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistFullDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistShortDto;

import java.util.List;

public interface WishlistService {
    List<WishlistFullDto> findAll();

    WishlistFullDto findById(Long wishlistId);

    WishlistShortDto create(CreateWishlistDto createWishlistDto);

    WishlistShortDto update(Long wishlistId, UpdateWishlistDto updateWishlistDto);

    WishlistFullDto delete(Long wishlistId);
}
