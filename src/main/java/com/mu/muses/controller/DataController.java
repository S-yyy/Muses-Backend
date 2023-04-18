package com.mu.muses.controller;

import com.mu.muses.dto.Dashboard;
import com.mu.muses.entity.Enums;
import com.mu.muses.service.DatasetService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class DataController {
    @Autowired
    DatasetService datasetService;

    @Operation(summary = "影像类型分布趋势")
    @GetMapping(value = "/api/database/dashboard/dist/images")
    @ResponseBody
    public List<Map<String,Object>> getDistImages(){
        return datasetService.findDistImages();
    }

    @Operation(summary = "各体部影像数据量分布")
    @GetMapping(value = "/api/database/dashboard/dist/parts")
    @ResponseBody
    public List<Map<String,Object>> getParts(){
        return datasetService.findStep1();
    }

    @Operation(summary = "单体部内各疾病的影像数据量分布")
    @GetMapping(value = "/api/database/dashboard/dist/illness")
    @ResponseBody
    public List<Map<String,Object>> getIllness(@RequestParam String parts){
        return datasetService.findStep2(parts);
    }

    @Operation(summary = "单疾病下各亚型的影像数据量分布")
    @GetMapping(value = "/api/database/dashboard/dist/illness/subtype")
    @ResponseBody
    public List<Map<String,Object>> getIllnessSubtype(@RequestParam String illness){
        return datasetService.findStep3(illness);
    }

}
