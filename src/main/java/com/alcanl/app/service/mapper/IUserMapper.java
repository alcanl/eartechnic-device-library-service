package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface IUserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
