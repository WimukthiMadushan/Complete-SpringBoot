package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dto.RegisterUserRequest;
import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
    User toEntity(RegisterUserRequest request);
}
