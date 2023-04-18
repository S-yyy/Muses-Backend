package com.mu.muses.dao;

import com.mu.muses.entity.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ResearchDao extends JpaRepository<Research,Integer>{
    @Modifying
    void deleteById(int id);

    @Query(value = "select status as label ,count(*) as value from research group by status",nativeQuery = true)
    List<Map<String, Object>> findStatus();
}
