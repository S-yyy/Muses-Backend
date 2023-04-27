package com.mu.muses.controller;

import com.mu.muses.config.RestResponse;
import com.mu.muses.enums.TrendType;
import com.mu.muses.service.CaseDataService;
import com.mu.muses.service.DatasetService;
import com.mu.muses.service.TrendDataService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(tags = "数据大盘看板")
public class DataController {
    @Autowired
    DatasetService datasetService;
    @Autowired
    CaseDataService caseDataService;
    @Autowired
    TrendDataService trendDataService;

    @Operation(summary = "累计病人数趋势")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/trend/cases")
    @ResponseBody
    public RestResponse getDashboardTrendCases(){
        return RestResponse.success(trendDataService.getTrend(TrendType.CaseData));
    }

    @Operation(summary = "累计影像病例数趋势")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/trend/images")
    @ResponseBody
    public RestResponse getTrendImages(){
        return RestResponse.success(trendDataService.getTrend(TrendType.Images));
    }

    @Operation(summary = "影像类型分布趋势")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/images")
    @ResponseBody
    public RestResponse getDistImages(){
        return RestResponse.success(caseDataService.findDistImages());
    }

    @Operation(summary = "各体部影像数据量分布")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/parts")
    @ResponseBody
    public RestResponse getParts(){
        return RestResponse.success(caseDataService.findStep1());
    }

    @Operation(summary = "单体部内各疾病的影像数据量分布")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/illness")
    @ResponseBody
    public RestResponse getIllness(@RequestParam String parts){
        return RestResponse.success(caseDataService.findStep2(parts));
    }

    @Operation(summary = "单疾病下各亚型的影像数据量分布")
    @CrossOrigin
    @GetMapping(value = "/api/database/dashboard/dist/illness/subtype")
    @ResponseBody
    public RestResponse getIllnessSubtype(@RequestParam String illness){
        return RestResponse.success(caseDataService.findStep3(illness));
    }

}
