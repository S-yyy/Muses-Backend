package com.mu.muses.service;

import com.mu.muses.dao.CaseDataDao;
import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseDataService {
    @Autowired
    CaseDataDao caseDataDao;

    public boolean save(CaseData caseData){
        if (caseDataDao.save(caseData)!=null){
            return true;
        }
        else {
            return false;
        }
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
        return caseDataDao.getById(id);
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

}
