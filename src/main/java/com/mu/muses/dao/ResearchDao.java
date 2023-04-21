package com.mu.muses.dao;

import com.mu.muses.entity.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ResearchDao extends JpaRepository<Research,Integer>{
    @Modifying
    void deleteById(int id);

    Research findById(int id);

    @Query(value = "select status as label ,count(*) as value from research group by status",nativeQuery = true)
    List<Map<String, Object>> findStatus();

    @Query(value = "select * from research where opening_time >= :date and status <> 6",nativeQuery = true)
    List<Research> getThisWeek(@Param("date") Date date);

    @Query(value = "select * from research where opening_time >= :start and opening_time < :end and status <> 6",nativeQuery = true)
    List<Research> getLastWeek(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select opening_time as label ,count(*) as value from research where status <> 6 group by opening_time order by opening_time desc ",nativeQuery = true)
    List<Map<String, Object>> findSumByDate();
}
