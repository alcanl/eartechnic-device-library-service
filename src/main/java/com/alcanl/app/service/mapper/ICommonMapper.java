package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.*;
import com.alcanl.app.service.mapper.converter.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICommonMapper {

    @LibraryToLibraryNameConverter
    default String libraryToLibraryName(Library library)
    {
        return library.libId;
    }

    @ParamToParamNameConverter
    default String paramToParamName(Param param)
    {
        return param.paramId;
    }

    @ParamToParamFileConverter
    default byte[] paramToParamFile(Param param)
    {
        return param.paramData;
    }
    @UserToUserIdConverter
    default long userToUserId(User user)
    {
        return user.userId;
    }

    @HearingAidToModelNumberConverter
    default int modelNumberToModelNumber(HearingAid hearingAid) { return hearingAid.modelNumber; }

    @EqualizerValuesToEqualizerValuesIdConverter
    default long equalizerValuesToEqualizerValuesId(EqualizerValues equalizerValues)
    {
        return equalizerValues.equalizerValuesId;
    }

}
