package ru.otus.kupipodariday.wishlist.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.otus.kupipodariday.wishlist.dto.CreateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.UpdateWishlistDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistFullDto;
import ru.otus.kupipodariday.wishlist.dto.WishlistShortDto;
import ru.otus.kupipodariday.wishlist.model.Wishlist;
import ru.otus.kupipodariday.wishlist.model.WishlistView;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Wishlist updateModel(CreateWishlistDto createWishlistDto, @MappingTarget Wishlist wishlist);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Wishlist updateModel(UpdateWishlistDto updateWishlistDto, @MappingTarget Wishlist wishlist);

    WishlistShortDto toDto(Wishlist wishlist);

    WishlistFullDto toDto(WishlistView wishlist);

    List<WishlistFullDto> toDto(List<WishlistView> wishlists);
}
