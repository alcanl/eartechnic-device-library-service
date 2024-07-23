package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.EqualizerValues;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IEqualizerValuesRepository extends CrudRepository<EqualizerValues, Long> {

    Optional<EqualizerValues> findEqualizerValuesByEqualizerValuesName(String name);

    Optional<EqualizerValues> findEqualizerValuesByEqualizerValuesId(Long id);

}
