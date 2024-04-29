package com.alcanl.app.repository;

import com.alcanl.app.repository.entity.FittingInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IFittingInfoRepository extends CrudRepository<FittingInfo, Long> {

    @Query("FROM FittingInfo fi WHERE fi.user.userId = :userId")
    Iterable<FittingInfo> findByUserId(long userId);

    @Query(value = "select * from fitting_info where date_part('day', fittingDate) = :day and date_part('month', fittingDate) = :month and date_part('year', fittingDate) = :year", nativeQuery = true)
    Iterable<FittingInfo> findByDate(@Param("day")int day, @Param("month")int month, @Param("year")int year);

    @Query(value = "select * from fitting_info fi where date_part('day', fittingDate) = :day and date_part('month', fittingDate) = :month and date_part('year', fittingDate) = :year and fi.user_id = :id", nativeQuery = true)
    Iterable<FittingInfo> findByDateAndUserId(@Param("id") long id, @Param("day")int day, @Param("month")int month, @Param("year")int year);
}
