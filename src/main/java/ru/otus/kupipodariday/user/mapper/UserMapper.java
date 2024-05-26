package ru.otus.kupipodariday.user.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.otus.kupipodariday.auth.dto.SignUpRequest;
import ru.otus.kupipodariday.user.dto.UpdateUserDto;
import ru.otus.kupipodariday.user.dto.UserDto;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.model.UserView;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateModel(SignUpRequest signUpRequest, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateModel(UpdateUserDto updateUserDto, @MappingTarget User user);

    UserDto toDto(User user);

    UserDto toDto(UserView user);

    List<UserDto> toDto(List<UserView> users);
}
