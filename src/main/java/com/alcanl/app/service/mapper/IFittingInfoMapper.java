package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.FittingInfo;
import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.dto.FittingInfoDTO;
import com.alcanl.app.service.mapper.converter.ParamToParamNameConverter;
import com.alcanl.app.service.mapper.converter.UserToUserIdConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(implementationName = "FittingMapperImpl", componentModel = "spring", uses = ICommonMapper.class)
public interface IFittingInfoMapper {

    @Mapping(target = "fittingId", ignore = true)
    default FittingInfo toFittingInfo(FittingInfoDTO fittingInfoDTO, Param param, User user)
    {
        if (fittingInfoDTO == null || param == null || user == null)
            return null;

        var fittingInfo = new FittingInfo();
        fittingInfo.savedParam = param;
        fittingInfo.user = user;
        fittingInfo.fittingDate = LocalDateTime.now();

        return fittingInfo;
    }

    @Mapping(source = "user", target = "userId", qualifiedBy = UserToUserIdConverter.class)
    @Mapping(source = "savedParam", target = "paramId", qualifiedBy = ParamToParamNameConverter.class)
    @Mapping(source = "fittingDate", target = "fittingDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    FittingInfoDTO toFittingInfoDTO(FittingInfo fittingInfo);
}
