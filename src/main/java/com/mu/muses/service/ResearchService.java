package com.mu.muses.service;

import com.mu.muses.dao.JournalDao;
import com.mu.muses.dao.ResearchDao;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.entity.JournalInformation;
import com.mu.muses.entity.Research;
import com.mu.muses.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ResearchService {
    @Autowired
    ResearchDao researchDao;
    @Autowired
    JournalDao journalDao;

    public boolean save(Research research){
        researchDao.save(research);
        return true;
    }

    public void delete(int id){
        researchDao.deleteById(id);
    }

    public List<Map<String, Object>> findStatus(){
        return researchDao.findStatus();
    }

    public Research findById(int id){
        return researchDao.findById(id);
    }

    public Page<Research> findAll(int pageNum, int size){
        Pageable pageable = PageRequest.of(pageNum, size);
        return researchDao.findAll(pageable);
    }

    public List<Research> findAll(){
        return researchDao.findAll();
    }

    public List<Dashboard> getStatusEnums(){
        List<Dashboard> list = new ArrayList<>();
        list.add(new Dashboard(Status.Opening.getIndex(),Status.Opening.getName()));
        list.add(new Dashboard(Status.Collection.getIndex(),Status.Collection.getName()));
        list.add(new Dashboard(Status.Marking.getIndex(),Status.Marking.getName()));
        list.add(new Dashboard(Status.Analysis.getIndex(),Status.Analysis.getName()));
        list.add(new Dashboard(Status.Writing.getIndex(),Status.Writing.getName()));
        list.add(new Dashboard(Status.Ending.getIndex(),Status.Ending.getName()));
        list.add(new Dashboard(Status.Over.getIndex(),Status.Over.getName()));
        return list;
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

    public JournalInformation saveJournal(JournalInformation journalInformation){
        return journalDao.save(journalInformation);
    }



}
