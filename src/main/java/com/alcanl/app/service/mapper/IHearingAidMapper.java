package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.service.dto.HearingAidDTO;
import com.alcanl.app.service.mapper.converter.EqualizerValuesToEqualizerValuesIdConverter;
import com.alcanl.app.service.mapper.converter.LibraryToLibraryNameConverter;
import com.alcanl.app.service.mapper.converter.ParamToParamNameConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "HearingAidMapperImpl", componentModel = "spring", uses = ICommonMapper.class)
public interface IHearingAidMapper {

    default HearingAid toHearingAid(HearingAidDTO hearingAidDTO, Library library, Param param)
    {
        if (library == null || param == null)
            return null;

        else {
            var hearingAid = new HearingAid();
            hearingAid.library = library;
            hearingAid.modelNumber = hearingAidDTO.getModelNumber();
            hearingAid.activeParamId = hearingAidDTO.getActiveParamId();
            hearingAid.defaultParam = param;
            hearingAid.modelName = hearingAidDTO.getModelName();
            hearingAid.wdrcChannelCount = hearingAidDTO.getWdrcChannelCount();
            hearingAid.frequencyChannelCount = hearingAidDTO.getFrequencyChannelCount();
            return hearingAid;
        }
    }

    @Mapping(source = "equalizerValues", target = "equalizerValuesId", qualifiedBy = EqualizerValuesToEqualizerValuesIdConverter.class)
    @Mapping(source = "defaultParam", target = "defaultParamId", qualifiedBy = ParamToParamNameConverter.class)
    @Mapping(source = "library", target = "libraryId", qualifiedBy = LibraryToLibraryNameConverter.class)
    HearingAidDTO toHearingAidDTO(HearingAid hearingAid);

}
