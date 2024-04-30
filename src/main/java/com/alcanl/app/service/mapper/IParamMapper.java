package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.service.dto.ParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IParamMapper {
    @Mapping(source = "paramName", target = "paramId")
    @Mapping(source = "paramData", target = "params")
    @Mapping(target = "library", ignore = true)
    Param toParam(ParamDTO paramDTO);

    @Mapping(source = "paramId", target = "paramName")
    @Mapping(source = "params", target = "paramData")
    @Mapping(target = "libraryName", ignore = true)
    ParamDTO toParamDTO(Param param);
}
