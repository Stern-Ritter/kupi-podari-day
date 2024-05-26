package ru.otus.kupipodariday.wish.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.kupipodariday.wish.dto.CreateWishDto;
import ru.otus.kupipodariday.wish.dto.UpdateWishDto;
import ru.otus.kupipodariday.wish.dto.WishFullDto;
import ru.otus.kupipodariday.wish.dto.WishShortDto;
import ru.otus.kupipodariday.wish.service.WishService;

import java.util.List;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
@Slf4j
public class WishController {
    public static final int LAST_WISHES_LIMIT = 40;
    public static final int TOP_WISHES_LIMIT = 20;
    private final WishService wishService;

    @GetMapping("/last")
    public List<WishFullDto> findLastWishes() {
        return wishService.findLastWishes(LAST_WISHES_LIMIT);
    }

    @GetMapping("/top")
    public List<WishFullDto> findTopWishes() {
        return wishService.findTopWishes(TOP_WISHES_LIMIT);
    }

    @GetMapping("/{id}")
    public WishFullDto findWish(@PathVariable Long id) {
        return wishService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishShortDto createWish(@Valid @RequestBody CreateWishDto createWishDto) {
        return wishService.create(createWishDto);
    }

    @PostMapping("/{id}/copy")
    @ResponseStatus(HttpStatus.CREATED)
    public WishShortDto createWishCopy(@Valid @PathVariable Long id) {
        return wishService.createCopy(id);
    }

    @PatchMapping("/{id}")
    public WishFullDto updateWish(@PathVariable Long id, @Valid @RequestBody UpdateWishDto updateWishDto) {
        return wishService.update(id, updateWishDto);
    }

    @DeleteMapping("/{id}")
    public WishFullDto deleteWish(@PathVariable Long id) {
        return wishService.delete(id);
    }
}
