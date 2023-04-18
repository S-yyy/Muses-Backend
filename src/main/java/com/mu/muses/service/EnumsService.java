package com.mu.muses.service;

import com.mu.muses.dao.EnumsDao;
import com.mu.muses.entity.Enums;
import com.mu.muses.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnumsService {
    @Autowired
    EnumsDao enumsDao;

    public List<Enums> findAllByTreatmentType(){
        return enumsDao.findAllByTreatmentType();
    }



}
