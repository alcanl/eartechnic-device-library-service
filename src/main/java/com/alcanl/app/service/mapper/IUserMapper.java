package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.dto.UserDTO;
import com.alcanl.app.service.mapper.converter.HearingAidToModelNumberConverter;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "UserMapperImpl", componentModel = "spring", uses = ICommonMapper.class)
public interface IUserMapper {

    @InheritInverseConfiguration
    @Mapping(target = "hearingAid", ignore = true)
    @Mapping(target = "userId", ignore = true)
    User toUser(UserDTO userDTO);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "eMail", target = "EMail")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "nationalityNumber", target = "nationalityNumber")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "hearingAid", target = "hearingAidModelNumber", qualifiedBy = HearingAidToModelNumberConverter.class)
    @Mapping(source = "fittingInfo", target = "fittingInfo")
    UserDTO toUserDTO(User user);
}
