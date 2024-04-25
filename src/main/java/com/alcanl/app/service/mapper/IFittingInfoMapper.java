package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.FittingInfo;
import com.alcanl.app.service.dto.FittingInfoSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "FittingMapperImpl", componentModel = "spring")
public interface IFittingInfoMapper {

    FittingInfo toFittingInfo(FittingInfoSaveDTO fittingInfoDTO);

    @Mapping(source = "fittingDate", target = "fittingDate", dateFormat = "dd/MM/yyyy")
    FittingInfoSaveDTO toFittingInfoDTO(FittingInfo fittingInfo);
}
