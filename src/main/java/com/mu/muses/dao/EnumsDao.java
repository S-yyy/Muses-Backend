package com.mu.muses.dao;

import com.mu.muses.entity.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface EnumsDao extends JpaRepository<Enums, Integer> {

    @Query(value = "select * from treatmenttype", nativeQuery = true)
    List<Enums> findAllByTreatmentType();

    @Query(value = "select m.* from medicalImages m", nativeQuery = true)
    List<Enums> findAllByMedicalImages();

    @Query(value = "select b.* from bodyParts b", nativeQuery = true)
    List<Enums> findAllByBodyParts();

    @Query(value = "select i.* from illness i", nativeQuery = true)
    List<Enums> findAllByIllness();

    @Query(value = "select i.* from illnessSubtype i", nativeQuery = true)
    List<Enums> findAllByIllnessSubtype();

    @Query(value = "select m.* from medicalSection m", nativeQuery = true)
    List<Enums> findAllByMedicalSection();
}
