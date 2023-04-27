package com.mu.muses.service;

import com.mu.muses.dao.JournalDao;
import com.mu.muses.entity.JournalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JournalService {
    @Autowired
    JournalDao journalDao;

    public Page<JournalInformation> findJournalPage(int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return journalDao.findAll(pageable);
    }
}
