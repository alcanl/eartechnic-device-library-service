package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.FittingInfo;
import com.alcanl.app.service.dto.FittingInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "FittingMapperImpl", componentModel = "spring")
public interface IFittingInfoMapper {

    @Mapping(target = "fittingId", ignore = true)
    FittingInfo toFittingInfo(FittingInfoDTO fittingInfoDTO);

    @Mapping(source = "fittingDate", target = "fittingDate", dateFormat = "dd/MM/yyyy")
    FittingInfoDTO toFittingInfoDTO(FittingInfo fittingInfo);
}
