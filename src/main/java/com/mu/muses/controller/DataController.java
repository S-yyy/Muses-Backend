package com.mu.muses.controller;

import com.mu.muses.config.RestResponse;
import com.mu.muses.dto.Dashboard;
import com.mu.muses.dto.DashboardData;
import com.mu.muses.entity.Enums;
import com.mu.muses.service.CaseDataService;
import com.mu.muses.service.DatasetService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Controller
public class DataController {
    @Autowired
    DatasetService datasetService;
    @Autowired
    CaseDataService caseDataService;

    @Operation(summary = "累计病人数趋势")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/trend/cases")
    @ResponseBody
    public RestResponse getDashboardTrendCases(){
        DashboardData data = new DashboardData();
        data.total = caseDataService.findAll().size();
        data.value = caseDataService.findThisWeek().size();
        int lastWeek = caseDataService.findLastWeek().size();
        if(lastWeek == 0){
            data.rate = "100";
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
        data.data = caseDataService.getNumByDate();
        return RestResponse.success(data);
    }

    @Operation(summary = "影像类型分布趋势")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/images")
    @ResponseBody
    public RestResponse getDistImages(){
        return RestResponse.success(datasetService.findDistImages());
    }

    @Operation(summary = "各体部影像数据量分布")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/parts")
    @ResponseBody
    public RestResponse getParts(){
        return RestResponse.success(datasetService.findStep1());
    }

    @Operation(summary = "单体部内各疾病的影像数据量分布")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/illness")
    @ResponseBody
    public RestResponse getIllness(@RequestParam String parts){
        return RestResponse.success(datasetService.findStep2(parts));
    }

    @Operation(summary = "单疾病下各亚型的影像数据量分布")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/illness/subtype")
    @ResponseBody
    public RestResponse getIllnessSubtype(@RequestParam String illness){
        return RestResponse.success(datasetService.findStep3(illness));
    }

}
