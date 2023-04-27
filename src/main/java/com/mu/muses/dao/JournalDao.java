package com.mu.muses.dao;

import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.JournalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface JournalDao extends JpaRepository<JournalInformation,Integer> {
    @Query(value = "select * from journalinformation where create_date >= :date ",nativeQuery = true)
    List<JournalInformation> getThisWeek(@Param("date") Date date);

    @Query(value = "select * from journalinformation where create_date >= :start and create_date < :end",nativeQuery = true)
    List<JournalInformation> getLastWeek(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select create_date as label ,count(*) as value from journalinformation group by create_date order by create_date desc ",nativeQuery = true)
    List<Map<String, Object>> findSumByDate();
}
