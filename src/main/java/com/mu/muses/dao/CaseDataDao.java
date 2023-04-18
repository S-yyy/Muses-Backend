package com.mu.muses.dao;


import com.mu.muses.entity.CaseData;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CaseDataDao extends JpaRepository<CaseData,Integer> {
    @Transactional
    @Modifying
    void deleteById(int id);

    List<CaseData> findAllByPatientId(int patientId);

    CaseData findByPatientIdAndVisitDate(int patientId,String visitDate);



}
