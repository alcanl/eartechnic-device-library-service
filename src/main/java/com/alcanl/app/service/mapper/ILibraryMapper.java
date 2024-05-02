package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.service.dto.LibraryDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "LibraryMapperImpl", componentModel = "spring")
public interface ILibraryMapper {
    @Mapping(source = "libId", target = "libraryName")
    @Mapping(source = "libFile", target = "libraryData")
    LibraryDTO toLibraryDTO(Library library);

    @InheritInverseConfiguration
    @Mapping(target = "params", ignore = true)
    @Mapping(target = "hearingAid", ignore = true)
    Library toLibrary(LibraryDTO libraryDTO);
}
