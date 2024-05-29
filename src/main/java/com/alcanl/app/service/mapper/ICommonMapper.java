package com.alcanl.app.service.mapper;

import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.mapper.converter.LibraryToLibraryNameConverter;
import com.alcanl.app.service.mapper.converter.ParamToParamFileConverter;
import com.alcanl.app.service.mapper.converter.ParamToParamNameConverter;
import com.alcanl.app.service.mapper.converter.UserToUserIdConverter;
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

}
