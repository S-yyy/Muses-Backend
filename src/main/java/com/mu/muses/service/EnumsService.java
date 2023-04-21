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

    public List<Enums> findMedicalImagesEnums(){
        return enumsDao.findAllByMedicalImages();
    }

    public List<Enums> findIllnessEnums(){
        return enumsDao.findAllByIllness();
    }

    public List<Enums> findIllnessSubtypeEnums(){
        return enumsDao.findAllByIllnessSubtype();
    }

    public List<Enums> findMedicalSectionEnums(){
        return enumsDao.findAllByMedicalSection();
    }


    public List<Enums> findBodyPartsEnums() {
        return enumsDao.findAllByBodyParts();
    }
}
