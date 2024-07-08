package com.alcanl.app.repository.dal;

import com.alcanl.app.repository.IHearingAidRepository;
import com.alcanl.app.repository.ILibraryRepository;
import com.alcanl.app.repository.IParamRepository;
import com.alcanl.app.repository.IUserRepository;
import com.alcanl.app.repository.entity.*;
import com.alcanl.app.repository.IFittingInfoRepository;
import com.karandev.util.data.repository.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Slf4j
@Component
public class LibraryServiceDataHelper {
    private final IHearingAidRepository m_hearingAidRepository;
    private final ILibraryRepository m_libraryRepository;
    private final IParamRepository m_paramRepository;
    private final IUserRepository m_userRepository;
    private final IFittingInfoRepository m_fittingInfoRepository;
    public LibraryServiceDataHelper(IHearingAidRepository hearingAidRepository, ILibraryRepository libraryRepository,
                                    IParamRepository paramRepository, IUserRepository _userRepository, IFittingInfoRepository fittingInfoRepository)
    {
        m_hearingAidRepository = hearingAidRepository;
        m_libraryRepository = libraryRepository;
        m_paramRepository = paramRepository;
        m_userRepository = _userRepository;
        m_fittingInfoRepository = fittingInfoRepository;
    }

    public Optional<byte []> findDefaultParamDataByHearingAid(HearingAid hearingAid)
    {
        try {

            var libOpt = m_paramRepository.findByHearingAid(hearingAid.modelNumber);

            return libOpt.map(param -> param.paramData);

        } catch (Throwable ex) {

            log.error("Error finding default param data by hearing aid: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidParams", ex);
        }
    }
    public Optional<Param> findDefaultParamByHearingAid(HearingAid hearingAid)
    {
        try {
            return m_paramRepository.findByHearingAid(hearingAid.modelNumber);

        } catch (Throwable ex) {
            log.error("Error finding default param by hearing aid: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidParams", ex);
        }
    }

    public Optional<Param> findParamByHearingAid(HearingAid hearingAid)
    {
        try {
            return m_paramRepository.findByHearingAid(hearingAid.modelNumber);
        } catch (Throwable ex) {
            log.error("Error finding param by hearing aid: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidParam", ex);
        }
    }

    public Iterable<Param> findParamsByLibrary(Library library)
    {
        try {
            return m_paramRepository.findByLibrary(library);
        } catch (Throwable ex) {
            log.error("Error finding params by library: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::getLibraryParam", ex);
        }
    }
    public Optional<User> saveUser(User user)
    {
        try {
            if (m_userRepository.existsById(user.userId))
                return Optional.empty();

            return Optional.of(m_userRepository.save(user));
        } catch (Throwable ex) {
            log.error("Error saving user: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::saveUser", ex);
        }
    }
    public Optional<FittingInfo> saveFittingInfo(FittingInfo fittingInfo)
    {
        try {
            return Optional.of(m_fittingInfoRepository.save(fittingInfo));
        } catch (Throwable ex) {
            log.error("Error saving fitting info: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::saveFittingInfo", ex);
        }
    }
    public Optional<HearingAid> saveHearingAid(HearingAid hearingAid)
    {
        try {
            return m_hearingAidRepository.existsById(hearingAid.modelName) ? Optional.empty()
                    : Optional.of(m_hearingAidRepository.save(hearingAid));

        } catch (Throwable ex) {
            log.error("Error saving hearing aid: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::saveHearingAid", ex);
        }
    }
    public Optional<Library> saveLibrary(Library library)
    {
        try {
            if (m_libraryRepository.existsById(library.libId))
                return Optional.empty();

            return Optional.of(m_libraryRepository.save(library));

        } catch (Throwable ex) {
            log.error("Error saving library: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::saveLibrary", ex);
        }
    }
    public Optional<Param> saveParam(Param param)
    {
        try {
            if (m_paramRepository.existsById(param.paramId))
                return Optional.empty();

            return Optional.of(m_paramRepository.save(param));
        } catch (Throwable ex) {
            log.error("Error saving param: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::saveParam", ex);
        }
    }
    public Optional<byte[]> findLibraryDataByHearingAidModelName(String modelName)
    {
        try {
            var hearingAidOpt = m_hearingAidRepository.findById(modelName);
            if (hearingAidOpt.isPresent())
                return m_libraryRepository.findLibraryDataByHearingAidModel(modelName);


            return Optional.empty();

        } catch (Throwable ex) {
            log.error("Error finding library data by hearing aid model name: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findLibraryByHearingAidModelName", ex);
        }
    }
    public Optional<Library> findLibraryByHearingAidModelName(String modelName)
    {
        try {
           return m_libraryRepository.findLibraryIdByHearingAidModel(modelName)
                   .flatMap(m_libraryRepository::findById);

        } catch (Throwable ex) {
            log.error("Error finding library by hearing aid model name: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findLibraryByHearingAidModelName", ex);
        }
    }
    public Optional<Library> findLibraryById(String name)
    {
        try {
            return m_libraryRepository.findById(name);
        } catch (Throwable ex) {
            log.error("Error finding library by id: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findLibraryById", ex);
        }
    }
    public Iterable<HearingAid> findHearingAidsByLibraryId(String libraryId)
    {
        try {
            return m_hearingAidRepository.findByLibrary(libraryId);
        } catch (Throwable ex) {
            log.error("Error finding hearing aids by library id: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidByLibraryId", ex);
        }
    }
    public Iterable<HearingAid> findHearingAidByParamId(String paramId)
    {
        try {
            return m_hearingAidRepository.findByParam(paramId);
        } catch (Throwable ex) {
            log.error("Error finding hearing aids by param id: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidByParamId", ex);
        }
    }
    public Optional<HearingAid> findHearingAidById(String modelName)
    {
        try {
            return m_hearingAidRepository.findById(modelName);
        } catch (Throwable ex) {
            log.error("Error finding hearing aid by id: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidById", ex);
        }
    }
    public Optional<Param> findParamById(String paramId)
    {
        try {
            return m_paramRepository.findById(paramId);
        } catch (Throwable ex) {
            log.error("Error finding param by id: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findParamById", ex);
        }
    }
    public Optional<User> findUserByEmailAndPassword(String eMail, String password)
    {
        try {
            return m_userRepository.findByeMailAndPassword(eMail, password);
        } catch (Throwable ex) {
            log.error("Error finding user by email and password: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findUserByEmailAndPassword", ex);
        }
    }
    public Iterable<FittingInfo> findFittingInfoByUser(User user)
    {
        try {
            return m_fittingInfoRepository.findByUser(user);
        } catch (Throwable ex) {
            log.error("Error finding fitting info by user: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findFittingInfoByUser", ex);
        }
    }
    public Optional<HearingAid> findHearingAidByModelNumber(int modelNumber)
    {
        try {
            return m_hearingAidRepository.findModelNameByModelNumber(modelNumber)
                    .flatMap(m_hearingAidRepository::findById);

        } catch (Throwable ex) {
            log.error("Error finding hearing aid by model number: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidByModelNumber", ex);
        }
    }
    public Optional<User> findUserById(long id)
    {
        try {
            return m_userRepository.findById(id);
        } catch (Throwable ex) {
            log.error("Error finding user by id: {}", ex.getMessage());
            throw new RepositoryException("LibraryServiceDataHelper::findByUserId", ex);
        }
    }
}
