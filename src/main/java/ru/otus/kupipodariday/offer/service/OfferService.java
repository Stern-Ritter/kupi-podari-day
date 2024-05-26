package ru.otus.kupipodariday.offer.service;

import ru.otus.kupipodariday.offer.dto.CreateOfferDto;
import ru.otus.kupipodariday.offer.dto.ManyOfferDto;
import ru.otus.kupipodariday.offer.dto.OfferShortDto;
import ru.otus.kupipodariday.offer.dto.OneOfferDto;

import java.util.List;

public interface OfferService {
    List<ManyOfferDto> findAll();

    OneOfferDto findById(Long offerId);

    OfferShortDto create(CreateOfferDto dto);
}
