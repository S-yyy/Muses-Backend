package com.mu.muses.service;

import com.mu.muses.dao.CaseDataDao;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class CaseDataService {
    @Autowired
    CaseDataDao caseDataDao;

    public List<CaseData> findAll(){
        return caseDataDao.findAll();
    }



    public CaseData save(CaseData caseData){
        return caseDataDao.save(caseData);
    }

    public boolean saveAll(List<CaseData> caseDatas){
        if (caseDataDao.saveAll(caseDatas)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public void deleteByID(int id){
        caseDataDao.deleteById(id);
    }

    public CaseData findById(int id){
        return caseDataDao.findById(id);
    }

    public List<CaseData> findByPatientId(int patientId){
        return caseDataDao.findAllByPatientId(patientId);
    }

    public CaseData findByPatientIdAndDate(int patientId,String date){
        return caseDataDao.findByPatientIdAndVisitDate(patientId,date);
    }


    public Page<CaseData> findAll(CaseData caseData, int pageNum, int size){
        Example<CaseData> example = Example.of(caseData);
        Pageable pageable = PageRequest.of(pageNum, size);
        Page<CaseData> page = caseDataDao.findAll(example,pageable);
        System.out.println(page.getTotalElements());
        return caseDataDao.findAll(example,pageable);
    }

    public List<Map<String,Object>> findStep1(){
        return caseDataDao.find1();
    }

    public List<Map<String,Object>> findStep2(String parts){
        return caseDataDao.find2(parts);
    }

    public List<Map<String,Object>> findStep3(String illness){
        return caseDataDao.find3(illness);
    }

    public List<Map<String, Object>> findDistImages() {
        return caseDataDao.findDist();
    }
}
