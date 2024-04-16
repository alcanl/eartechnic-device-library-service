package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IParamRepository extends CrudRepository<Param, Long> {
    Iterable<Param> findByLibrary(Library library);

    @Query("FROM Param p WHERE p.hearingAid = :hearingAid ")
    Optional<Param> findByHearingAid(HearingAid hearingAid);
}
