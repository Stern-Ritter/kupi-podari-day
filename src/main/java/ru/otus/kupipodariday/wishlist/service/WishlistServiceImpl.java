package ru.otus.kupipodariday.wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kupipodariday.exceptions.ForbiddenException;
import ru.otus.kupipodariday.exceptions.NotFoundException;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.service.UserService;
import ru.otus.kupipodariday.wish.model.Wish;
import ru.otus.kupipodariday.wish.storage.WishRepository;
import ru.otus.kupipodariday.wishlist.dto.CreateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.UpdateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistFullDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistShortDto;
import ru.otus.kupipodariday.wishlist.mapper.WishlistMapper;
import ru.otus.kupipodariday.wishlist.model.Wishlist;
import ru.otus.kupipodariday.wishlist.model.WishlistView;
import ru.otus.kupipodariday.wishlist.storage.WishlistRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final UserService userService;

    private final WishlistRepository wishlistRepository;
    private final WishRepository wishRepository;

    private final WishlistMapper wishlistMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<WishlistFullDto> findAll() {
        List<WishlistView> wishlists = wishlistRepository.findWishlistsByOrderByCreatedAtDesc();
        return wishlistMapper.toDto(wishlists);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public WishlistFullDto findById(Long wishlistId) {
        WishlistView wishlist = wishlistRepository.findWishlistById(wishlistId)
                .orElseThrow(() -> new NotFoundException(String.format("Вишлист с id:%d не найден", wishlistId)));

        return wishlistMapper.toDto(wishlist);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishlistShortDto create(CreateWishlistDto createWishlistDto) {
        User currentUser = userService.getCurrentUser();

        Set<Wish> wishes = new HashSet<>(wishRepository.findAllById(createWishlistDto.getItemsId()));
        for (Wish wish : wishes) {
            if(!wish.getOwner().getId().equals(currentUser.getId())) {
                throw new ForbiddenException("Нельзя добавлять в свой вишлист желаемые подарки других пользователей");
            }
        }


        Wishlist wishlist = wishlistMapper.updateModel(createWishlistDto, new Wishlist());
        wishlist.setOwner(currentUser);
        wishlist.setWishes(wishes);

        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDto(savedWishlist);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishlistShortDto update(Long wishlistId, UpdateWishlistDto updateWishlistDto) {
        User currentUser = userService.getCurrentUser();
        Wishlist savedWishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new NotFoundException(String.format("Вишлист с id:%d не найден", wishlistId)));

        if (!savedWishlist.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("Нельзя редактировать чужие вишлисты");
        }

        Set<Wish> wishes = new HashSet<>(wishRepository.findAllById(updateWishlistDto.getItemsId()));

        Wishlist wishlist = wishlistMapper.updateModel(updateWishlistDto, savedWishlist);
        wishlist.setWishes(wishes);

        Wishlist updatedWishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDto(updatedWishlist);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishlistFullDto delete(Long wishlistId) {
        User currentUser = userService.getCurrentUser();
        WishlistView wishlist = wishlistRepository.findWishlistById(wishlistId)
                .orElseThrow(() -> new NotFoundException(String.format("Вишлист с id:%d не найден", wishlistId)));

        if (!wishlist.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("Нельзя редактировать чужие вишлисты");
        }

        wishlistRepository.deleteById(wishlistId);

        return wishlistMapper.toDto(wishlist);
    }
}
