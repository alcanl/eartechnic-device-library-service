package com.alcanl.app.service;

import com.alcanl.app.repository.dal.LibraryServiceDataHelper;
import com.alcanl.app.service.dto.*;
import com.alcanl.app.service.mapper.*;
import com.karandev.util.data.repository.exception.RepositoryException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class LibraryDataService {
    private final LibraryServiceDataHelper m_libraryServiceDataHelper;
    private final IFittingInfoMapper m_fittingInfoMapper;
    private final ILibraryMapper m_libraryMapper;
    private final IParamMapper m_paramMapper;
    private final IHearingAidMapper m_hearingAidMapper;
    private final IUserMapper m_userMapper;

    public LibraryDataService(LibraryServiceDataHelper libraryServiceDataHelper, IFittingInfoMapper fittingInfoMapper,
                              ILibraryMapper libraryMapper, IParamMapper paramMapper, IHearingAidMapper hearingAidMapper,
                              IUserMapper userMapper)
    {
        m_libraryServiceDataHelper = libraryServiceDataHelper;
        m_fittingInfoMapper = fittingInfoMapper;
        m_libraryMapper = libraryMapper;
        m_paramMapper = paramMapper;
        m_hearingAidMapper = hearingAidMapper;
        m_userMapper = userMapper;
    }
    public void saveFittingInfo(FittingInfoDTO fittingInfoDTO)
    {
        try {
            m_libraryServiceDataHelper.saveFittingInfo(m_fittingInfoMapper.toFittingInfo(fittingInfoDTO));
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveFittingInfo", ex);
        }
    }
    public void saveUser(UserDTO userDTO)
    {
        try {
            m_libraryServiceDataHelper.saveUser(m_userMapper.toUser(userDTO));
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveUser", ex);
        }
    }
    public void saveHearingAid(HearingAidDTO hearingAidDTO)
    {
        try {
            var libOpt = m_libraryServiceDataHelper.findLibraryById(hearingAidDTO.getLibraryId());
            var defaultParamOpt = m_libraryServiceDataHelper.findParamById(hearingAidDTO.getDefaultParamId());
            var activeParamOpt = m_libraryServiceDataHelper.findParamById(hearingAidDTO.getActiveParamId());

            if (libOpt.isPresent() && defaultParamOpt.isPresent() && activeParamOpt.isPresent())
                m_libraryServiceDataHelper.saveHearingAid(
                        m_hearingAidMapper.toHearingAid(hearingAidDTO, libOpt.get(), defaultParamOpt.get())
                );

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::saveHearingAid", ex);
        }
    }
    public void saveParam(ParamDTO paramDTO)
    {
        try {
            var library = m_libraryServiceDataHelper.findLibraryById(paramDTO.getLibraryName());

            var param = m_paramMapper.toParam(paramDTO,
                    library.orElseThrow(() ->
                            new ServiceException("LibraryDataService::saveParam, Undefined Library")));

            m_libraryServiceDataHelper.saveParam(param);

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
            m_libraryServiceDataHelper.findHearingAidsByLibraryId(libraryId)
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

    public Optional<HearingAidDTO> findHearingAidByModelName(String hearingAidModelName)
    {
        try {
            return m_libraryServiceDataHelper.findHearingAidById(hearingAidModelName).map(
                    m_hearingAidMapper::toHearingAidDTO);

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findHearingAidByModelName", ex);
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

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModel", ex);
        }
    }
    public Optional<LibraryDTO> findLibraryInfoByHearingAidModel(String hearingAidModel)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryInfoByHearingAidModelName(hearingAidModel);

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModel", ex);
        }
    }
    public Optional<UserDTO> findUserByEmailAndPassword(String eMail, String password)
    {
        try {
            return m_libraryServiceDataHelper.findUserByEmailAndPassword(eMail, password).map(m_userMapper::toUserDTO);
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findUserByEmailAndPassword", ex);
        }
    }
    public Iterable<FittingInfoDTO> findFittingInfoByUser(UserDTO userDTO)
    {
        try {
            var userOpt = m_libraryServiceDataHelper.findUserByEmailAndPassword(
                    userDTO.getEMail(), userDTO.getPassword()
            );

            return userOpt.map(user -> StreamSupport.stream(m_libraryServiceDataHelper.findFittingInfoByUser(user)
                    .spliterator(), false).map(m_fittingInfoMapper::toFittingInfoDTO).toList()).orElse(null);

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findFittingInfoByUser", ex);
        }
    }
    public Iterable<ParamDTO> findParamsByLibrary(LibraryDTO libraryDTO)
    {
        try {
            var libOpt = m_libraryServiceDataHelper.findLibraryById(libraryDTO.getLibraryName());

            return libOpt.map(library -> StreamSupport.stream(m_libraryServiceDataHelper.findParamsByLibrary(library).spliterator()
            , false).map(m_paramMapper::toParamDTO).toList()).orElse(null);
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findParamsByLibrary", ex);
        }
    }
    public Optional<ParamDTO> findParamByHearingAid(String modelName)
    {
        try {
            var hearingAidOpt = m_libraryServiceDataHelper.findHearingAidById(modelName);

            return hearingAidOpt.flatMap(hearingAid -> m_libraryServiceDataHelper.findParamByHearingAid(hearingAid).map(m_paramMapper::toParamDTO));

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findParamByHearingAid", ex);
        }
    }
    public Optional<byte[]> findDefaultParamDataByHearingAid(String modelName)
    {
        try {
            var hearingAidOpt = m_libraryServiceDataHelper.findHearingAidById(modelName);

            return hearingAidOpt.flatMap(m_libraryServiceDataHelper::findDefaultParamDataByHearingAid);
        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findParamDataByHearingAid", ex);
        }
    }
    public Optional<byte[]> findActiveParamDataByUser(UserDTO userDTO)
    {
        try {
            var user = m_libraryServiceDataHelper.findUserByEmailAndPassword(userDTO.getEMail(), userDTO.getPassword());

            return user.flatMap(u -> m_libraryServiceDataHelper.findHearingAidById(u.hearingAid.modelName)
                    .flatMap(ha -> m_libraryServiceDataHelper.findParamById(ha.activeParamId))
                    .map(p -> p.paramData));

        } catch (RepositoryException | NullPointerException ex ) {
            throw new ServiceException("LibraryDataService::findParamDataByUser", ex);
        }
    }
    public Optional<HearingAidDTO> findHearingAidByModelNumber(int modelNumber)
    {
        try {
            return m_libraryServiceDataHelper.findHearingAidByModelNumber(modelNumber)
                    .map(m_hearingAidMapper::toHearingAidDTO);

        } catch (RepositoryException ex) {
            throw new ServiceException("LibraryDataService::findHearingAidByModelNumber", ex);
        }
    }
    public Optional<String> findHearingAidModelNameByModelNumber(int modelNumber)
    {
        return findHearingAidByModelNumber(modelNumber).map(HearingAidDTO::getModelName);
    }
    public Optional<Integer> findHearingAidModelNumberByModelName(String modelName)
    {
        return findHearingAidByModelName(modelName).map(HearingAidDTO::getModelNumber);
    }
}
