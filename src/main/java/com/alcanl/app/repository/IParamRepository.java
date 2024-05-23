package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IParamRepository extends CrudRepository<Param, String> {
    Iterable<Param> findByLibrary(Library library);

    @Query(value = "SELECT * FROM param_info pi WHERE pi.param_id = (SELECT param_id FROM hearing_aid ha WHERE ha.model_number = :modelNumber)", nativeQuery = true)
    Optional<Param> findByHearingAid(int modelNumber);

}
