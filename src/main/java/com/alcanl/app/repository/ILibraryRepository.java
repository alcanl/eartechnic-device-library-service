package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILibraryRepository extends CrudRepository<Library, String> {
    Optional<Library> findByHearingAid(HearingAid hearingAid);
}
