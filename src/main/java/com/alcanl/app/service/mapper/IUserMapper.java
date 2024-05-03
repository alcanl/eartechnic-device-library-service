package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.dto.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "UserMapperImpl", componentModel = "spring")
public interface IUserMapper {

    @InheritInverseConfiguration
    @Mapping(target = "userId", ignore = true)
    User toUser(UserDTO userDTO);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "eMail", target = "EMail")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "hearingAid", target = "hearingAid")
    @Mapping(source = "fittingInfo", target = "fittingInfo")
    UserDTO toUserDTO(User user);
}
