package com.aegis.TechMarket.Mappers;

import com.aegis.TechMarket.DataTransferObjects.UserDto;
import com.aegis.TechMarket.Entities.User;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto (User user);
}

