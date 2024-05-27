package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.HearingAid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface IHearingAidRepository extends CrudRepository<HearingAid, String> {
    @Query("FROM HearingAid ha WHERE ha.defaultParam.paramId = :paramId")
    Iterable<HearingAid> findByParam(String paramId);

    @Query("FROM HearingAid ha WHERE ha.library.libId = :libraryId")
    Iterable<HearingAid> findByLibrary(String libraryId);

    @Query(value = "SELECT hearing_aid_model_name FROM hearing_aid ha WHERE ha.model_number = :modelNumber", nativeQuery = true)
    Optional<String> findModelNameByModelNumber(int modelNumber);

}
