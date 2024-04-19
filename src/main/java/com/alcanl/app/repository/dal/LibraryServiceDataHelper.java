package com.alcanl.app.repository.dal;

import com.alcanl.app.repository.IHearingAidRepository;
import com.alcanl.app.repository.ILibraryRepository;
import com.alcanl.app.repository.IParamRepository;
import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import com.karandev.util.data.repository.exception.RepositoryException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class LibraryServiceDataHelper {
    private final IHearingAidRepository m_hearingAidRepository;
    private final ILibraryRepository m_libraryRepository;
    private final IParamRepository m_paramRepository;
    public LibraryServiceDataHelper(IHearingAidRepository hearingAidRepository, ILibraryRepository libraryRepository,
                                    IParamRepository paramRepository)
    {
        m_hearingAidRepository = hearingAidRepository;
        m_libraryRepository = libraryRepository;
        m_paramRepository = paramRepository;
    }

    public Optional<byte []> getHearingAidParams(HearingAid hearingAid)
    {
        try {

            var libOpt = m_paramRepository.findByHearingAid(hearingAid);

            return libOpt.map(param -> param.params);

        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidParams", ex);
        }
    }

    public Optional<Param> getHearingAidParam(HearingAid hearingAid)
    {
        try {
            return m_paramRepository.findByHearingAid(hearingAid);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidParam", ex);
        }
    }

    public Iterable<Param> getLibraryParam(Library library)
    {
        try {
            return m_paramRepository.findByLibrary(library);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::getLibraryParam", ex);
        }
    }
    public void saveHearingAid(HearingAid hearingAid)
    {
        try {
            m_hearingAidRepository.save(hearingAid);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::saveHearingAid", ex);
        }
    }
    public void saveLibrary(Library library)
    {
        try {
            m_libraryRepository.save(library);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::saveLibrary", ex);
        }
    }
    public void saveParam(Param param)
    {
        try {
            m_paramRepository.save(param);
        } catch (Throwable ex) {
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
            throw new RepositoryException("LibraryServiceDataHelper::findLibraryByHearingAidModelName");
        }
    }
    public Optional<Library> findLibraryById(String name)
    {
        try {
            return m_libraryRepository.findById(name);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::findLibraryById");
        }
    }
    public Iterable<HearingAid> findHearingAidByLibraryId(String libraryId)
    {
        try {
            return m_hearingAidRepository.findByLibrary(libraryId);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidByLibraryId");
        }
    }
    public Iterable<HearingAid> findHearingAidByParamId(String paramId)
    {
        try {
            return m_hearingAidRepository.findByParam(paramId);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidByParamId");
        }
    }
    public Optional<HearingAid> findHearingAidById(String modelName)
    {
        try {
            return m_hearingAidRepository.findById(modelName);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::findHearingAidById");
        }
    }
}
