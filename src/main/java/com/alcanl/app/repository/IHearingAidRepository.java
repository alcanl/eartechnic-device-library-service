package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.HearingAid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHearingAidRepository extends CrudRepository<HearingAid, String> {
}
