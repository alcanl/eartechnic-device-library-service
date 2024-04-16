package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IHearingAidRepository extends CrudRepository<HearingAid, String> {
    Optional<HearingAid> findByParam(Param param);

    Optional<Library> findByLibrary(Library library);

}
