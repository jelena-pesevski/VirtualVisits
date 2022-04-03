package org.unibl.etf.virtualvisits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unibl.etf.virtualvisits.models.entities.VirtualVisitEntity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface VirtualVisitEntityRepository extends JpaRepository<VirtualVisitEntity, Integer> {

    //all active visits
    @Query(value = "select * from virtual_visit v where v.date = :currDate and v.start <= :currTime and ADDTIME(v.start, v.duration) > :currTime", nativeQuery = true)
    List<VirtualVisitEntity> findAllActiveVisits(@Param("currDate") Date currDate, @Param("currTime")Time currTime);

    //all upcoming for museum
    @Query(value = "select v from VirtualVisitEntity v where v.museum.museumId=:id and ((v.date > :currDate) or  (v.date = :currDate and v.start > :currTime))")
    List<VirtualVisitEntity> findAllUpcomingByMuseumId(@Param("currDate") Date currDate, @Param("currTime")Time currTime, Integer id);

    @Query(value = "select * from virtual_visit v where v.virtual_visit_id=:id and v.date = :currDate and v.start <= :currTime and ADDTIME(v.start, v.duration) > :currTime", nativeQuery = true)
    Optional<VirtualVisitEntity> findActiveVisitById(@Param("id") Integer id, @Param("currDate") Date currDate, @Param("currTime")Time currTime);

    //all upcoming
    @Query(value = "select v from VirtualVisitEntity v where v.date > :currDate or  (v.date = :currDate and v.start > :currTime)")
    List<VirtualVisitEntity> findAllUpcoming(@Param("currDate") Date currDate, @Param("currTime")Time currTime);
}
