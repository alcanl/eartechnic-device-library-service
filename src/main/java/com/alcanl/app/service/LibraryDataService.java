package com.alcanl.app.service;

import com.alcanl.app.repository.dal.LibraryServiceDataHelper;
import com.alcanl.app.service.dto.*;
import com.alcanl.app.service.mapper.*;
import com.karandev.util.data.repository.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Slf4j
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
    public Optional<FittingInfoDTO> saveFittingInfo(FittingInfoDTO fittingInfoDTO)
    {
        try {
            var userOpt = m_libraryServiceDataHelper.findUserById(fittingInfoDTO.getUserId());
            var paramOpt = m_libraryServiceDataHelper.findParamById(fittingInfoDTO.getParamId());

            if (userOpt.isPresent() && paramOpt.isPresent())
                return m_libraryServiceDataHelper.saveFittingInfo(m_fittingInfoMapper.toFittingInfo(
                        fittingInfoDTO, paramOpt.get(), userOpt.get()))
                        .map(m_fittingInfoMapper::toFittingInfoDTO);

            return Optional.empty();

        } catch (RepositoryException ex) {
            log.error("Error in service, saveFittingInfo: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::saveFittingInfo", ex);
        }
    }

    @Transactional
    public Optional<UserDTO> saveUser(UserDTO userDTO)
    {
        try {
            var hearingAid = m_libraryServiceDataHelper.findHearingAidByModelNumber(userDTO.getHearingAidModelNumber());

            if (hearingAid.isPresent()) {
                var user = m_userMapper.toUser(userDTO);
                user.hearingAid = hearingAid.get();
                return m_libraryServiceDataHelper.saveUser(user).map(m_userMapper::toUserDTO);
            }

            return Optional.empty();
        } catch (RepositoryException ex) {
            log.error("Error in service, saveUser: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::saveUser", ex);
        }
    }

    @Transactional
    public Optional<HearingAidDTO> saveHearingAid(HearingAidDTO hearingAidDTO)
    {
        try {
            var libOpt = m_libraryServiceDataHelper.findLibraryById(hearingAidDTO.getLibraryId());
            var defaultParamOpt = m_libraryServiceDataHelper.findParamById(hearingAidDTO.getDefaultParamId());
            var activeParamOpt = m_libraryServiceDataHelper.findParamById(hearingAidDTO.getActiveParamId());

            if (libOpt.isPresent() && defaultParamOpt.isPresent() && activeParamOpt.isPresent())
                return m_libraryServiceDataHelper.saveHearingAid(
                        m_hearingAidMapper.toHearingAid(
                                hearingAidDTO, libOpt.get(), defaultParamOpt.get()))
                        .map(m_hearingAidMapper::toHearingAidDTO);

            return Optional.empty();

        } catch (RepositoryException ex) {
            log.error("Error in service, saveHearingAid: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::saveHearingAid", ex);
        }
    }
    public Optional<ParamDTO> saveParam(ParamDTO paramDTO)
    {
        try {
            var library = m_libraryServiceDataHelper.findLibraryById(paramDTO.getLibraryName());

            var param = m_paramMapper.toParam(paramDTO,
                    library.orElseThrow(() ->
                            new ServiceException("LibraryDataService::saveParam, Undefined Library")));

            return m_libraryServiceDataHelper.saveParam(param).map(m_paramMapper::toParamDTO);

        } catch (RepositoryException ex) {
            log.error("Error in service, saveParam: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::saveParam", ex);
        }
    }
    public Optional<LibraryDTO> saveLibrary(LibraryDTO libraryDTO)
    {
        try {
            return m_libraryServiceDataHelper.saveLibrary(m_libraryMapper.toLibrary(libraryDTO))
                    .map(m_libraryMapper::toLibraryDTO);
        } catch (RepositoryException ex) {
            log.error("Error in service, saveLibrary: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::saveLibrary", ex);
        }
    }
    public LibraryToHearingAidsDTO findHearingAidsByLibrary(String libraryId)
    {
        try {
            var hearingAidList = new LibraryToHearingAidsDTO();
            m_libraryServiceDataHelper.findHearingAidsByLibraryId(libraryId)
                    .forEach(ha -> hearingAidList.hearingAids.add(new HearingAidDTO(
                            ha.modelName, ha.library.libId, ha.defaultParam.paramId, ha.wdrcChannelCount, ha.frequencyChannelCount)));

            return hearingAidList;

        } catch (RepositoryException ex) {
            log.error("Error in service, findHearingAidsByLibrary: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findHearingAidsByLibrary", ex);
        }
    }

    public ParamToHearingAidsDTO findHearingAidsByParam(String paramId)
    {
        try {
            var hearingAidList = new ParamToHearingAidsDTO();

            m_libraryServiceDataHelper.findHearingAidByParamId(paramId)
                    .forEach(ha -> hearingAidList.hearingAidDTOs.add(new HearingAidDTO(
                            ha.modelName, ha.library.libId, ha.defaultParam.paramId, ha.wdrcChannelCount, ha.frequencyChannelCount)));

            return hearingAidList;

        } catch (RepositoryException ex) {
            log.error("Error in service, findHearingAidsByParam: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findHearingAidsByParam", ex);
        }
    }

    public Optional<HearingAidDTO> findHearingAidByModelName(String hearingAidModelName)
    {
        try {
            return m_libraryServiceDataHelper.findHearingAidById(hearingAidModelName)
                    .map(m_hearingAidMapper::toHearingAidDTO);

        } catch (RepositoryException ex) {
            log.error("Error in service, findHearingAidByModelName: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findHearingAidByModelName", ex);
        }
    }

    public Optional<LibraryDTO> findLibraryById(String libraryName)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryById(libraryName).map(
                    m_libraryMapper::toLibraryDTO);

        } catch (RepositoryException ex) {
            log.error("Error in service, findLibraryById: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findLibraryById", ex);
        }
    }
    public Optional<byte[]> findLibraryDataByHearingAidModel(String hearingAidModel)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryDataByHearingAidModelName(hearingAidModel);

        } catch (RepositoryException ex) {
            log.error("Error in service, findLibraryDataByHearingAidModel: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModel", ex);
        }
    }
    public Optional<LibraryDTO> findLibraryInfoByHearingAidModel(String hearingAidModel)
    {
        try {
            return m_libraryServiceDataHelper.findLibraryByHearingAidModelName(hearingAidModel)
                    .map(m_libraryMapper::toLibraryDTO);

        } catch (RepositoryException ex) {
            log.error("Error in service, findLibraryInfoByHearingAidModel: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findLibraryByHearingAidModel", ex);
        }
    }
    public Optional<UserDTO> findUserByEmailAndPassword(String eMail, String password)
    {
        try {
            return m_libraryServiceDataHelper.findUserByEmailAndPassword(eMail, password).map(m_userMapper::toUserDTO);
        } catch (RepositoryException ex) {
            log.error("Error in service, findUserByEmailAndPassword: {}", ex.getMessage());
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
            log.error("Error in service, findFittingInfoByUser: {}", ex.getMessage());
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
            log.error("Error in service, findParamsByLibrary: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findParamsByLibrary", ex);
        }
    }
    public Optional<ParamDTO> findParamByHearingAid(String modelName)
    {
        try {
            var hearingAidOpt = m_libraryServiceDataHelper.findHearingAidById(modelName);

            return hearingAidOpt.flatMap(hearingAid -> m_libraryServiceDataHelper.findParamByHearingAid(hearingAid).map(m_paramMapper::toParamDTO));

        } catch (RepositoryException ex) {
            log.error("Error in service, findParamByHearingAid: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findParamByHearingAid", ex);
        }
    }
    public Optional<byte[]> findDefaultParamDataByHearingAid(String modelName)
    {
        try {
            var hearingAidOpt = m_libraryServiceDataHelper.findHearingAidById(modelName);

            return hearingAidOpt.flatMap(m_libraryServiceDataHelper::findDefaultParamDataByHearingAid);
        } catch (RepositoryException ex) {
            log.error("Error in service, findDefaultParamDataByHearingAid: {}", ex.getMessage());
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
            log.error("Error in service, findActiveParamDataByUser: {}", ex.getMessage());
            throw new ServiceException("LibraryDataService::findParamDataByUser", ex);
        }
    }
    public Optional<HearingAidDTO> findHearingAidByModelNumber(int modelNumber)
    {
        try {
            return m_libraryServiceDataHelper.findHearingAidByModelNumber(modelNumber)
                    .map(m_hearingAidMapper::toHearingAidDTO);

        } catch (RepositoryException ex) {
            log.error("Error in service, findHearingAidByModelNumber: {}", ex.getMessage());
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
    public Optional<ParamDTO> findDefaultParamByHearingAidModelNumber(int modelNumber)
    {
        var hearingAid = m_libraryServiceDataHelper.findHearingAidByModelNumber(modelNumber);

        return hearingAid.flatMap(aid -> m_libraryServiceDataHelper.findDefaultParamByHearingAid(aid)
                .map(m_paramMapper::toParamDTO));

    }
}
