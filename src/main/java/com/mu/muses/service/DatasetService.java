package com.mu.muses.service;

import com.mu.muses.dao.DatasetDao;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.dto.DatabaseQuery;
import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatasetService {
    @Autowired
    DatasetDao datasetDao;

    public boolean save(Dataset dataset){
        if(datasetDao.save(dataset)!=null){
            return true;
        }
        else
            return false;
    }

    public boolean delete(int id){
        if (datasetDao.findById(id)==null){
            return false;
        }
        else{
            datasetDao.deleteById(id);
            return true;
        }
    }

    public Page<Dataset> findAll(Dataset dataset, int pageNum, int size){
        Example<Dataset> example = Example.of(dataset);
        Pageable pageable = PageRequest.of(pageNum, size);
        return datasetDao.findAll(example,pageable);
    }

    public Page<Dataset> findDash(DatabaseQuery databaseQuery){
        Pageable pageable = PageRequest.of(databaseQuery.pageNo, databaseQuery.pageSize);
        List<Dataset> datasets = datasetDao.findDash(databaseQuery.owner,databaseQuery.topic);
        Page<Dataset> pages = new PageImpl<>(datasets,pageable,datasets.size());
        return pages;
    }

    public List<Map<String,Object>> findStep1(){
        return datasetDao.find1();
    }

    public List<Map<String,Object>> findStep2(String parts){
        return datasetDao.find2(parts);
    }

    public List<Map<String,Object>> findStep3(String illness){
        return datasetDao.find3(illness);
    }

    public List<Map<String, Object>> findDistImages() {
        return datasetDao.findDist();
    }
}
