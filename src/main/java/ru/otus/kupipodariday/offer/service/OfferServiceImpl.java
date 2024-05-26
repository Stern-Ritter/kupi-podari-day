package ru.otus.kupipodariday.offer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kupipodariday.exceptions.BadRequestException;
import ru.otus.kupipodariday.exceptions.NotFoundException;
import ru.otus.kupipodariday.offer.dto.CreateOfferDto;
import ru.otus.kupipodariday.offer.dto.ManyOfferDto;
import ru.otus.kupipodariday.offer.dto.OfferShortDto;
import ru.otus.kupipodariday.offer.dto.OneOfferDto;
import ru.otus.kupipodariday.offer.mapper.OfferMapper;
import ru.otus.kupipodariday.offer.model.ManyOfferView;
import ru.otus.kupipodariday.offer.model.Offer;
import ru.otus.kupipodariday.offer.model.OneOfferView;
import ru.otus.kupipodariday.offer.storage.OfferRepository;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.service.UserService;
import ru.otus.kupipodariday.wish.model.Wish;
import ru.otus.kupipodariday.wish.storage.WishRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final UserService userService;

    private final OfferRepository offerRepository;
    private final WishRepository wishRepository;

    private final OfferMapper offerMapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<ManyOfferDto> findAll() {
        List<ManyOfferView> offers = offerRepository.findOffersByOrderByCreatedAtDesc();

        return offerMapper.toDto(offers);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public OneOfferDto findById(Long offerId) {
        OneOfferView offer = offerRepository.findOfferById(offerId)
                .orElseThrow(() -> new NotFoundException(String.format("Взнос на подарок с id:%d не найден", offerId)));

        return offerMapper.toDto(offer);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public OfferShortDto create(CreateOfferDto dto) {
        User currentUser = userService.getCurrentUser();

        Long itemId = dto.getItemId();
        Wish wish = wishRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(String.format("Подарок с id:%d не найден", itemId)));

        if (wish.getPrice().equals(wish.getRaised())) {
            throw new BadRequestException("Нельзя скинуться на подаркок, на который уже собраны деньги.");
        }

        if (dto.getAmount() + wish.getRaised() > wish.getPrice()) {
            throw new BadRequestException("Сумма собранных средств не может превышать стоимость подарка.");
        }

        if (wish.getOwner().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Нельзя вносить деньги на собственные подарки.");
        }

        wish.setRaised(wish.getRaised() + dto.getAmount());
        wishRepository.save(wish);

        Offer offer = offerMapper.toModel(dto);
        offer.setUser(currentUser);
        offer.setWish(wish);

        Offer updatedOffer = offerRepository.save(offer);

        return offerMapper.toDto(updatedOffer);
    }
}
