package ru.otus.kupipodariday.wishlist.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.kupipodariday.wishlist.dto.CreateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.UpdateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistFullDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistShortDto;
import ru.otus.kupipodariday.wishlist.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/wishlistlists")
@RequiredArgsConstructor
@Slf4j
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping
    public List<WishlistFullDto> findAllWishlists() {
        return wishlistService.findAll();
    }

    @GetMapping("/{id}")
    public WishlistFullDto findWishlist(@PathVariable long id) {
        return wishlistService.findById(id);
    }

    @PostMapping
    public WishlistShortDto createWishlist(@Valid @RequestBody CreateWishlistDto createWishlistDto) {
        return wishlistService.create(createWishlistDto);
    }

    @PatchMapping("/{id}")
    public WishlistShortDto updateWishlist(@PathVariable long id, @Valid @RequestBody UpdateWishlistDto updateWishlistDto) {
        return wishlistService.update(id, updateWishlistDto);
    }

    @DeleteMapping("/{id}")
    public WishlistFullDto deleteWishlist(@PathVariable long id) {
        return wishlistService.delete(id);
    }
}
