package org.unibl.etf.virtualvisits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;

import java.util.List;

public interface LogEntityRepository extends JpaRepository<LogEntity, Integer> {

    List<LogEntity> findAllByAction(String action);

    @Query(value = "select hour(date_time) as hour, count(distinct(username)) as numOfUsers from log where date_time > (now()-INTERVAL 24 HOUR) and action=:action group by hour(date_time)", nativeQuery = true)
    List<HourNumOfUsersPair> findAllByActionWithinLast24Hours(String action);

    interface HourNumOfUsersPair {
        Integer getHour();
        Long getNumOfUsers();
    }

}
