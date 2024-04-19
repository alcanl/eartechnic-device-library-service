package com.alcanl.app.service;

import com.alcanl.app.repository.dal.LibraryServiceDataHelper;
import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.service.dto.LibraryToHearingAidsDTO;
import com.alcanl.app.service.dto.ParamToHearingAidsDTO;
import com.google.gson.GsonBuilder;
import com.karandev.util.data.repository.exception.RepositoryException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class LibraryDataService {
    private final LibraryServiceDataHelper m_libraryServiceDataHelper;

    public LibraryDataService(LibraryServiceDataHelper libraryServiceDataHelper)
    {
        m_libraryServiceDataHelper = libraryServiceDataHelper;
    }
    public LibraryToHearingAidsDTO findHearingAidsByLibrary(String libraryId)
    {
        try {
            var hearingAidList = new LibraryToHearingAidsDTO();
            m_libraryServiceDataHelper.findHearingAidByLibraryId(libraryId)
                    .forEach(ha -> hearingAidList.hearingAids.add(ha));

            return hearingAidList;

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findHearingAidsByLibrary", ex);
        }
    }

    public ParamToHearingAidsDTO findHearingAidsByParam(String paramId)
    {
        try {
            var hearingAidList = new ParamToHearingAidsDTO();

            m_libraryServiceDataHelper.findHearingAidByParamId(paramId)
                    .forEach(ha -> hearingAidList.hearingAids.add(ha));

            return hearingAidList;

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findHearingAidsByParam", ex);
        }
    }

    public Optional<HearingAid> findHearingAidByModelName(String hearingAidModelName)
    {
        try {
            return m_libraryServiceDataHelper.findHearingAidById(hearingAidModelName);
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModelName", ex);
        }
    }

    public Optional<Library> findLibraryById(String libraryName)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryById(libraryName);

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findLibraryById", ex);
        }
    }
    public Optional<byte[]> findLibraryDataByHearingAidModel(String hearingAidModel)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryDataByHearingAidModelName(hearingAidModel);

        } catch (Throwable ex) {
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModel", ex);
        }
    }
}
