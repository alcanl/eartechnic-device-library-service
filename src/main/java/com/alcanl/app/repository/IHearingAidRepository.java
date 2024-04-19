package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IHearingAidRepository extends CrudRepository<HearingAid, String> {
    @Query("FROM HearingAid ha WHERE ha.param.paramId = :paramId")
    Iterable<HearingAid> findByParam(String paramId);

    @Query("FROM HearingAid ha WHERE ha.library.libId = :libraryId")
    Iterable<HearingAid> findByLibrary(String libraryId);


}
