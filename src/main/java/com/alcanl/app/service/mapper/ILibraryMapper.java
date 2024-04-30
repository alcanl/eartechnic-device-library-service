package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.service.dto.LibraryDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ILibraryMapper {
    @Mapping(source = "libId", target = "libraryName")
    @Mapping(source = "libFile", target = "libraryData")
    LibraryDTO toLibraryDTO(Library library);

    @InheritInverseConfiguration
    Library toLibrary(LibraryDTO libraryDTO);
}
