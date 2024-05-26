package ru.otus.kupipodariday.wish.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kupipodariday.exceptions.ForbiddenException;
import ru.otus.kupipodariday.exceptions.NotFoundException;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.service.UserService;
import ru.otus.kupipodariday.wish.dto.CreateWishDto;
import ru.otus.kupipodariday.wish.dto.UpdateWishDto;
import ru.otus.kupipodariday.wish.dto.WishFullDto;
import ru.otus.kupipodariday.wish.dto.WishShortDto;
import ru.otus.kupipodariday.wish.mapper.WishMapper;
import ru.otus.kupipodariday.wish.model.Wish;
import ru.otus.kupipodariday.wish.model.WishView;
import ru.otus.kupipodariday.wish.storage.WishRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {
    private final UserService userService;
    private final WishRepository wishRepository;

    private final WishMapper wishMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<WishFullDto> findLastWishes(Integer limit) {
        List<Long> ids = wishRepository.findLastWishIds(limit);
        List<WishView> wishes = wishRepository.findWishesByIdIsInOrderByCreatedAtDesc(ids);

        return wishMapper.toDto(wishes);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<WishFullDto> findTopWishes(Integer limit) {
        List<Long> ids = wishRepository.findTopWishIds(limit);
        List<WishView> wishes = wishRepository.findWishesByIdIsInOrderByCopiedDescCreatedAtDesc(ids);

        return wishMapper.toDto(wishes);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<WishFullDto> findCurrentUserWishes() {
        User currentUser = userService.getCurrentUser();
        List<WishView> wishes = wishRepository.findWishesByOwnerUsernameOrderByCreatedAtDesc(currentUser.getUsername());

        return wishMapper.toDto(wishes);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<WishFullDto> findUserWishes(String username) {
        User user = userService.getByUsername(username);
        List<WishView> wishes = wishRepository.findWishesByOwnerUsernameOrderByCreatedAtDesc(user.getUsername());

        return wishMapper.toDto(wishes);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public WishFullDto findById(Long wishId) {
        WishView wish = wishRepository.findWishById(wishId)
                .orElseThrow(() -> new NotFoundException(String.format("Подарок с id:%d не найден", wishId)));

        return wishMapper.toDto(wish);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishShortDto create(CreateWishDto createWishDto) {
        User currentUser = userService.getCurrentUser();

        Wish wish = wishMapper.updateModel(createWishDto, new Wish());
        wish.setOwner(currentUser);

        Wish savedWish = wishRepository.save(wish);

        return wishMapper.toDto(savedWish);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishShortDto createCopy(Long wishId) {
        User currentUser = userService.getCurrentUser();
        Wish savedWish = wishRepository.findById(wishId)
                .orElseThrow(() -> new NotFoundException(String.format("Подарок с id:%d не найден", wishId)));

        savedWish.setCopied(savedWish.getCopied() + 1);
        wishRepository.save(savedWish);

        Wish copyWish = new Wish();
        copyWish.setName(savedWish.getName());
        copyWish.setLink(savedWish.getLink());
        copyWish.setImage(savedWish.getImage());
        copyWish.setPrice(savedWish.getPrice());
        copyWish.setDescription(savedWish.getDescription());
        copyWish.setOwner(currentUser);

        Wish updatedWish = wishRepository.save(copyWish);

        return wishMapper.toDto(updatedWish);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishFullDto update(Long wishId, UpdateWishDto updateWishDto) {
        User currentUser = userService.getCurrentUser();
        Wish savedWish = wishRepository.findById(wishId)
                .orElseThrow(() -> new NotFoundException(String.format("Подарок с id:%d не найден", wishId)));

        if (!savedWish.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("Нельзя редактировать желаемые подарки других пользователей");
        }

        if (savedWish.getOffers() != null && !savedWish.getOffers().isEmpty()) {
            updateWishDto.setPrice(savedWish.getPrice());
        }

        Wish wish = wishMapper.updateModel(updateWishDto, savedWish);

        wishRepository.save(wish);

        WishView updatedWish = wishRepository.findWishById(wishId)
                .orElseThrow(() -> new NotFoundException(String.format("Подарок с id:%d не найден", wishId)));

        return wishMapper.toDto(updatedWish);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public WishFullDto delete(Long wishId) {
        User currentUser = userService.getCurrentUser();
        WishView wish = wishRepository.findWishById(wishId)
                .orElseThrow(() -> new NotFoundException(String.format("Подарок с id:%d не найден", wishId)));

        if (!wish.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("Нельзя удалять желаемые подарки других пользователей");
        }

        wishRepository.deleteById(wishId);

        return wishMapper.toDto(wish);
    }
}
