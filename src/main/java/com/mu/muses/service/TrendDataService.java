package com.mu.muses.service;

import com.mu.muses.dao.CaseDataDao;
import com.mu.muses.dao.ResearchDao;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.dto.DashboardData;
import com.mu.muses.entity.CaseData;
import com.mu.muses.enums.TrendType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
public class TrendDataService {
    @Autowired
    CaseDataDao caseDataDao;
    @Autowired
    ResearchDao researchDao;

    public List<?> findThisWeek(TrendType trendType){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        Date thisWeek = new java.sql.Date(calendar.getTime().getTime());
        switch (trendType){
            case CaseData :
                return caseDataDao.getThisWeek(thisWeek);
            case Research:
                return researchDao.getThisWeek(thisWeek);
            case Images:
                return caseDataDao.getImagesThisWeek(thisWeek);
            default:
                return null;
        }
    }

    public List<?> findLastWeek(TrendType trendType){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        Date end = new java.sql.Date(calendar.getTime().getTime());
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        Date start = new java.sql.Date(calendar.getTime().getTime());
        switch (trendType){
            case CaseData :
                return caseDataDao.getLastWeek(start,end);
            case Research:
                return researchDao.getLastWeek(start,end);
            case Images:
                return caseDataDao.getImagesLastWeek(start,end);
            default:
                return null;
        }
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

    public List<Dashboard> getNumByDate(TrendType trendType){
        List<Map<String, Object>> result;
        switch (trendType){
            case CaseData :
                result = caseDataDao.findSumByDate();
                break;
            case Research:
                result = researchDao.findSumByDate();
                break;
            case Images:
                result = caseDataDao.findImageSumByDate();
                break;
            default:
                return null;
        }

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

    public DashboardData getTrend(TrendType trendType){
        DashboardData data = new DashboardData();
        data.total = researchDao.findAll().size();
        data.value = findThisWeek(trendType).size();
        int lastWeek = findLastWeek(trendType).size();
        if(lastWeek == 0){
            data.rate = "";
        }
        else{
            float rate = (data.value - lastWeek)/lastWeek;
            if (rate-(int)rate<0.00001){
                data.rate = String.valueOf((int)rate);
            }
            else{
                DecimalFormat df = new DecimalFormat("#.00");
                data.rate = df.format(rate);
            }
        }
        data.data = getNumByDate(trendType);
        return data;
    }

}
