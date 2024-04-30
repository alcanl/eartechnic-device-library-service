package com.alcanl.app.service;

import com.alcanl.app.repository.dal.LibraryServiceDataHelper;
import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Param;
import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.dto.*;
import com.alcanl.app.service.mapper.IFittingInfoMapper;
import com.alcanl.app.service.mapper.ILibraryMapper;
import com.alcanl.app.service.mapper.IParamMapper;
import com.karandev.util.data.repository.exception.RepositoryException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryDataService {
    private final LibraryServiceDataHelper m_libraryServiceDataHelper;
    private final IFittingInfoMapper m_fittingInfoMapper;
    private final ILibraryMapper m_libraryMapper;
    private final IParamMapper m_paramMapper;

    public LibraryDataService(LibraryServiceDataHelper libraryServiceDataHelper, IFittingInfoMapper fittingInfoMapper,
                              ILibraryMapper libraryMapper, IParamMapper paramMapper)
    {
        m_libraryServiceDataHelper = libraryServiceDataHelper;
        m_fittingInfoMapper = fittingInfoMapper;
        m_libraryMapper = libraryMapper;
        m_paramMapper = paramMapper;
    }
    public void saveFittingInfo(FittingInfoSaveDTO fittingInfoSaveDTO)
    {
        try {
            m_libraryServiceDataHelper.saveFittingInfo(m_fittingInfoMapper.toFittingInfo(fittingInfoSaveDTO));
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveFittingInfo", ex);
        }
    }
    public void saveUser(User user)
    {
        try {
            m_libraryServiceDataHelper.saveUser(user);
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveUser", ex);
        }
    }
    public void saveHearingAid(HearingAid hearingAid)
    {
        try {
            m_libraryServiceDataHelper.saveHearingAid(hearingAid);
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveHearingAid", ex);
        }
    }
    public Param saveParam(ParamDTO paramDTO)
    {
        try {
            var library = m_libraryServiceDataHelper.findLibraryById(paramDTO.getLibrary());
            var param = m_paramMapper.toParam(paramDTO);
            param.library = library.orElseThrow(() -> new ServiceException("LibraryDataService::saveParam, Undefined Library"));
            return m_libraryServiceDataHelper.saveParam(param);

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveParam", ex);
        }
    }
    public void saveLibrary(LibraryDTO libraryDTO)
    {
        try {
            m_libraryServiceDataHelper.saveLibrary(m_libraryMapper.toLibrary(libraryDTO));
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveLibrary", ex);
        }
    }
    public LibraryToHearingAidsDTO findHearingAidsByLibrary(String libraryId)
    {
        try {
            var hearingAidList = new LibraryToHearingAidsDTO();
            m_libraryServiceDataHelper.findHearingAidByLibraryId(libraryId)
                    .forEach(ha -> hearingAidList.hearingAids.add(new HearingAidDTO(
                            ha.modelName, ha.library.libId, ha.defaultParam.paramId)));

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
                    .forEach(ha -> hearingAidList.hearingAidDTOs.add(new HearingAidDTO(
                            ha.modelName, ha.library.libId, ha.defaultParam.paramId)));

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

    public Optional<LibraryDTO> findLibraryById(String libraryName)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryById(libraryName).map(
                    m_libraryMapper::toLibraryDTO);

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
    public Optional<LibraryDTO> findLibraryInfoByHearingAidModel(String hearingAidModel)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryInfoByHearingAidModelName(hearingAidModel);

        } catch (Throwable ex) {
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModel", ex);
        }
    }
}
