package ru.otus.kupipodariday.offer.mapper;

import org.mapstruct.Mapper;
import ru.otus.kupipodariday.offer.dto.CreateOfferDto;
import ru.otus.kupipodariday.offer.dto.ManyOfferDto;
import ru.otus.kupipodariday.offer.dto.OfferShortDto;
import ru.otus.kupipodariday.offer.dto.OneOfferDto;
import ru.otus.kupipodariday.offer.model.ManyOfferView;
import ru.otus.kupipodariday.offer.model.Offer;
import ru.otus.kupipodariday.offer.model.OneOfferView;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    Offer toModel(CreateOfferDto createOfferDto);

    OfferShortDto toDto(Offer offer);
    OneOfferDto toDto(OneOfferView offer);
    List<ManyOfferDto> toDto(List<ManyOfferView> offers);
}
