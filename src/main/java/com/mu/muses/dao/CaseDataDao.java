package com.mu.muses.dao;


import com.mu.muses.entity.CaseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface CaseDataDao extends JpaRepository<CaseData,Integer> {
    @Transactional
    @Modifying
    void deleteById(int id);

    CaseData findById(int id);

    List<CaseData> findAllByPatientId(int patientId);

    CaseData findByPatientIdAndVisitDate(int patientId,String visitDate);

    @Query(value = "select * from casedata where visit_date >= :date ",nativeQuery = true)
    List<CaseData> getThisWeek(@Param("date") Date date);

    @Query(value = "select * from casedata where visit_date >= :start and visit_date < :end",nativeQuery = true)
    List<CaseData> getLastWeek(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select * from casedata where visit_date >= :date and medical_images is not null",nativeQuery = true)
    List<CaseData> getImagesThisWeek(@Param("date") Date date);

    @Query(value = "select * from casedata where visit_date >= :start and visit_date < :end and medical_images is not null",nativeQuery = true)
    List<CaseData> getImagesLastWeek(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select visit_date as label ,count(*) as value from casedata group by visit_date order by visit_date desc ",nativeQuery = true)
    List<Map<String, Object>> findSumByDate();

    @Query(value = "select visit_date as label, count(*) as value from casedata where medical_images is not null group by visit_date order by visit_date desc ",nativeQuery = true)
    List<Map<String, Object>> findImageSumByDate();

    @Query(value = "select medical_images as label ,count(*) as value from casedata group by medical_images",nativeQuery = true)
    List<Map<String, Object>> findDist();

    @Query(value = "select body_parts as label ,count(*) as value from casedata group by body_parts",nativeQuery = true)
    List<Map<String,Object>> find1();

    @Query(value = "select illness as label ,count(*) as value from casedata where body_parts like :parts group by illness",nativeQuery = true)
    List<Map<String,Object>> find2(@Param("parts") String parts);

    @Query(value = "select illness_subtype as label ,count(*) as value from casedata where illness like :illness group by illness_subtype",nativeQuery = true)
    List<Map<String,Object>> find3(@Param("illness") String illness);


}
