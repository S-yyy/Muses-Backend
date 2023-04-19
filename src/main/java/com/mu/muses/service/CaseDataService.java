package com.mu.muses.service;

import com.mu.muses.dao.CaseDataDao;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.entity.CaseData;
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

    public List<CaseData> findThisWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        Date oneWeek = new java.sql.Date(calendar.getTime().getTime());
        return caseDataDao.getThisWeek(oneWeek);
    }

    public List<CaseData> findLastWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        Date end = new java.sql.Date(calendar.getTime().getTime());
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        Date start = new java.sql.Date(calendar.getTime().getTime());
        return caseDataDao.getlastWeek(start,end);
    }

    public java.sql.Date StringToDate(String sDate) {
        String str = sDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    public List<Dashboard> getNumByDate(){
        List<Map<String, Object>> result = caseDataDao.findSumByDate();
        List<Dashboard> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < 14;i++){
            Boolean find = false;
            Date date = new java.sql.Date(calendar.getTime().getTime());
            for(var item :result){
                if(item.get("label").toString().equals(date.toString())){
                    list.add(new Dashboard(Integer.parseInt((item.get("value").toString())),date.toString()));
                    find = true;
                    break;
                }
                else if(StringToDate(item.get("label").toString()).before(date)){
                    break;
                }
            }
            if(!find){
                list.add(new Dashboard(0,date.toString()));
            }
            calendar.add(Calendar.DAY_OF_MONTH,-1);
        }
        return list;
    }


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

}
