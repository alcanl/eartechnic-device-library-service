package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.service.mapper.converter.LibraryToLibraryNameConverter;
import com.alcanl.app.service.mapper.converter.ParamToParamNameConverter;
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

    @ParamToParamNameConverter
    default byte[] paramToParamFile(Param param)
    {
        return param.paramData;
    }
}
