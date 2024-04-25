package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.Library;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILibraryRepository extends CrudRepository<Library, String> {

    @Query(value = "select library_file from library_info li inner join hearing_aid ha on li.library_id = ha.lib_id  where ha.hearing_aid_model_name  = :hearingAidModel", nativeQuery = true)
    Optional<byte[]> findLibraryDataByHearingAidModel(String hearingAidModel);

    @Query(value = "select library_id from library_info li inner join hearing_aid ha on li.library_id = ha.lib_id  where ha.hearing_aid_model_name  = :hearingAidModel", nativeQuery = true)
    Optional<String> findLibraryIdByHearingAidModel(String hearingAidModel);

}
