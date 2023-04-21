package com.mu.muses.service;

import com.alibaba.fastjson.JSONArray;
import com.mu.muses.dao.DatasetDao;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.dto.DatabaseQuery;
import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        System.out.println(databaseQuery.owner);
        System.out.println(databaseQuery.topic);
        if (databaseQuery.topic == null){
            databaseQuery.topic = "";
        }
        List<Dataset> datasets;
        if(databaseQuery.owner == null){
            datasets = datasetDao.findDash(databaseQuery.topic);
        }
        else {
            datasets = datasetDao.findDash(databaseQuery.owner, databaseQuery.topic);
        }
        Page<Dataset> pages = new PageImpl<>(datasets,pageable,datasets.size());
        return pages;
    }

    public Dataset findById(int id){
        return datasetDao.findById(id);
    }

    public List<Dashboard> findTopics(){
        List<String> topics= datasetDao.findTopic();
        List<Dashboard> result = new ArrayList<>();
        List<String> topicList = new ArrayList<>();
        for(var item :topics){
            List<String> topic = JSONArray.parseArray(String.valueOf(item),String.class);
            topicList.addAll(topic);
        }
        Map<String, Long> resMap = topicList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(var item :resMap.keySet()){
            result.add(new Dashboard(resMap.get(item).intValue(),item));
        }
        return result;
    }



}
