package ru.otus.kupipodariday.wish.service;

import ru.otus.kupipodariday.wish.dto.CreateWishDto;
import ru.otus.kupipodariday.wish.dto.UpdateWishDto;
import ru.otus.kupipodariday.wish.dto.WishFullDto;
import ru.otus.kupipodariday.wish.dto.WishShortDto;

import java.util.List;

public interface WishService {
    List<WishFullDto> findLastWishes(Integer limit);

    List<WishFullDto> findTopWishes(Integer limit);

    List<WishFullDto> findCurrentUserWishes();

    List<WishFullDto> findUserWishes(String username);

    WishFullDto findById(Long wishId);

    WishShortDto create(CreateWishDto createWishDto);

    WishShortDto createCopy(Long wishId);

    WishFullDto update(Long wishId, UpdateWishDto updateWishDto);

    WishFullDto delete(Long wishId);
}
