package com.alcanl.app.repository.dal;

import com.alcanl.app.repository.IHearingAidRepository;
import com.alcanl.app.repository.ILibraryRepository;
import com.alcanl.app.repository.IParamRepository;
import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import com.karandev.util.data.repository.exception.RepositoryException;
import org.springframework.stereotype.Component;

import java.io.File;
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

    public Optional<File> getHearingAidLibFile(HearingAid hearingAid)
    {
        try {
            var libOpt = m_libraryRepository.findByHearingAid(hearingAid);

            return libOpt.map(library -> library.libFile);

        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidLibFile", ex);
        }
    }

    public Optional<File> getHearingAidParams(HearingAid hearingAid)
    {
        try {

            var libOpt = m_paramRepository.findByHearingAid(hearingAid);

            return libOpt.map(param -> param.params);

        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidParams", ex);
        }
    }

    public Optional<Library> getHearingAidLibrary(HearingAid hearingAid)
    {
        try {
            return m_libraryRepository.findByHearingAid(hearingAid);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::getHearingAidLibrary", ex);
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
    public Optional<HearingAid> findLibraryByHearingAidModelName(String modelName)
    {
        try {
            return m_hearingAidRepository.findById(modelName);
        } catch (Throwable ex) {
            throw new RepositoryException("LibraryServiceDataHelper::findLibraryByHearingAidModelName");
        }
    }
}
