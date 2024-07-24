package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.EqualizerValues;
import com.alcanl.app.service.dto.EqualizerValuesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(implementationName = "EqualizerValuesMapperImpl", componentModel = "spring")
public interface IEqualizerValuesMapper {

    EqualizerValuesDTO toEqualizerValuesDTO(EqualizerValues equalizerValues);

    @Mapping(target = "hearingAid", ignore = true)
    EqualizerValues toEqualizerValues(EqualizerValuesDTO equalizerValuesDTO);
}
