package ru.otus.kupipodariday.wish.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.otus.kupipodariday.offer.model.Offer;
import ru.otus.kupipodariday.wish.dto.CreateWishDto;
import ru.otus.kupipodariday.wish.dto.UpdateWishDto;
import ru.otus.kupipodariday.wish.dto.WishShortDto;
import ru.otus.kupipodariday.wish.model.Wish;
import ru.otus.kupipodariday.wish.dto.WishFullDto;
import ru.otus.kupipodariday.wish.model.WishView;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Wish updateModel(CreateWishDto createWishDto, @MappingTarget Wish wish);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Wish updateModel(UpdateWishDto updateWishDto, @MappingTarget Wish wish);

    @AfterMapping
    default void linkOffers(@MappingTarget Wish wish) {
        if(wish.getOffers() != null) {
            for(Offer offer: wish.getOffers()) {
                offer.setWish(wish);
            }
        }
    }

    WishShortDto toDto(Wish wish);
    WishFullDto toDto(WishView wish);
    List<WishFullDto> toDto(List<WishView> wishes);
}
