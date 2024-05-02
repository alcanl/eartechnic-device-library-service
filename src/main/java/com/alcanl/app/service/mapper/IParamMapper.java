package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.service.dto.ParamDTO;
import com.alcanl.app.service.mapper.converter.LibraryToLibraryNameConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "ParamMapperImpl", componentModel = "spring", uses = ICommonMapper.class)
public interface IParamMapper {

    @Mapping(source = "library", target = "libraryName", qualifiedBy = LibraryToLibraryNameConverter.class)
    ParamDTO toParamDTO(Param param);

    default Param toParam(ParamDTO dto, Library library)
    {
        Param param = new Param();
        param.paramId = dto.getParamId();
        param.library = library;
        param.paramData = dto.getParamData();

        return param;
    }
}
