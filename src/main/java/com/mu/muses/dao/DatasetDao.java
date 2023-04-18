package com.mu.muses.dao;

import com.mu.muses.dto.Dashboard;
import com.mu.muses.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DatasetDao extends JpaRepository<Dataset,Integer> {
    @Modifying
    void deleteById(int id);

    @Query(value = "select * from dataset where create_member like :member and topic_list like %:topic%",nativeQuery = true)
    List<Dataset> findDash(@Param("member") String owner,@Param("topic") String topic);

    @Query(value = "select * from dataset where create_member like :member",nativeQuery = true)
    List<Dataset> findDash(@Param("member") String owner);

    @Query(value = "select * from dataset ",nativeQuery = true)
    List<Dataset> findDash();

    @Query(value = "select medical_images as label ,count(*) as value from casedata group by medical_images",nativeQuery = true)
    List<Map<String, Object>> findDist();

    @Query(value = "select body_parts as label ,count(*) as value from casedata group by body_parts",nativeQuery = true)
    List<Map<String,Object>> find1();

    @Query(value = "select illness as label ,count(*) as value from casedata where body_parts like :parts group by illness",nativeQuery = true)
    List<Map<String,Object>> find2(@Param("parts") String parts);

    @Query(value = "select illness_subtype as label ,count(*) as value from casedata where illness like :illness group by illness_subtype",nativeQuery = true)
    List<Map<String,Object>> find3(@Param("illness") String illness);



}
