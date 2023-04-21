package com.mu.muses.dao;

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

    Dataset findById(int id);

    @Query(value = "select * from dataset where create_member = :member and topic_list like concat('%',:topic,'%') ",nativeQuery = true)
    List<Dataset> findDash(@Param("member") String owner,@Param("topic") String topic);

    @Query(value = "select * from dataset where topic_list like concat('%',:topic,'%') ",nativeQuery = true)
    List<Dataset> findDash(@Param("topic") String topic);

    @Query(value = "select * from dataset ",nativeQuery = true)
    List<Dataset> findDash();

    @Query(value = "select topic_list from dataset ",nativeQuery = true)
    List<String> findTopic();



}
