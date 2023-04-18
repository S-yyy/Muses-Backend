package com.mu.muses.controller;

import com.mu.muses.dto.CaseQuery;
import com.mu.muses.dto.DatabaseQuery;
import com.mu.muses.dto.Response;
import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.Dataset;
import com.mu.muses.entity.Enums;
import com.mu.muses.service.CaseDataService;
import com.mu.muses.service.DatasetService;
import com.mu.muses.service.EnumsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CaseDataController {
    @Autowired
    CaseDataService caseDataService;
    @Autowired
    EnumsService enumsService;
    @Autowired
    DatasetService datasetService;

    @Operation(summary = "查询病例")
    @PostMapping(value = "/api/database/cases")
    @ResponseBody
    public Response findCaseData(@RequestBody CaseQuery caseQuery){
        CaseData caseData = new CaseData();
        caseData.treatmentType = caseQuery.treatmentType;
        caseData.medicalImages = caseQuery.medicalImages;
        caseData.bodyParts = caseQuery.bodyParts;
        caseData.illness = caseQuery.illness;
        caseData.illnessSubtype = caseQuery.illnessSubtype;
        caseData.medicalSection = caseQuery.medicalSection;
        Response response =new Response();
        Page<CaseData> page = caseDataService.findAll(caseData, caseQuery.pageNo, caseQuery.pageSize);
        response.data = page.getContent().toArray();
        response.pageNum = caseQuery.pageNo;
        response.pageSize = page.getTotalPages();
        response.total = (int)page.getTotalElements();
        return response;
    }

    @Operation(summary = "录入病例")
    @PostMapping(value = "/api/database/cases/saveCaseData")
    @ResponseBody
    public Boolean saveCaseData(@RequestBody CaseData caseData){
        return caseDataService.save(caseData);
    }

    @Operation(summary = "批量录入病例")
    @PostMapping(value = "/api/database/cases/saveCaseDatas")
    @ResponseBody
    public Boolean saveCaseDatas(@RequestBody List<CaseData> caseDatas){
        return caseDataService.saveAll(caseDatas);
    }

    @Operation(summary = "删除病例")
    @DeleteMapping(value = "/api/database/cases/deletePostData")
    @ResponseBody
    public Boolean deleteCaseDatas(@RequestParam int id){
        if(caseDataService.findById(id)==null){
            return false;
        }
        else {
            caseDataService.deleteByID(id);
            return true;
        }
    }

    @Operation(summary = "查询患者所有时间节点病例列表")
    @GetMapping(value = "/api/database/cases/patients")
    @ResponseBody
    public List<CaseData> getCaseDatas(@RequestParam int patientId){
        return caseDataService.findByPatientId(patientId);
    }

    @Operation(summary = "查询患者某个时间点病例列表")
    @GetMapping(value = "/api/database/cases/patient")
    @ResponseBody
    public CaseData getPatientCaseData(@RequestParam int patientId,@RequestParam String visitDate){
        return caseDataService.findByPatientIdAndDate(patientId, visitDate);
    }

    @Operation(summary = "查询诊断类型枚举")
    @GetMapping(value = "/api/database/cases/treatmentType/enums")
    @ResponseBody
    public List<Enums> getTreatmentTypeEnums(){
        return enumsService.findAllByTreatmentType();
    }

    @Operation(summary = "查询数据集列表")
    @PostMapping(value = "/api/database/datasets")
    @ResponseBody
    public Response findDatasets(@RequestBody DatabaseQuery databaseQuery){
        Response response = new Response();
        Page<Dataset> page = datasetService.findDash(databaseQuery);
        response.data = page.getContent().toArray();
        response.total = (int) page.getTotalElements();
        response.pageSize = page.getTotalPages();
        response.pageNum = databaseQuery.pageNo;
        return response;
    }

    @Operation(summary = "保存或更新数据集")
    @PostMapping(value = "/api/database/datasets/saveDataset")
    @ResponseBody
    public boolean saveRole(@RequestBody Dataset dataset){
        return datasetService.save(dataset);
    }

    @Operation(summary = "删除数据集")
    @DeleteMapping(value = "/api/database/datasets/deleteDataset")
    @ResponseBody
    public boolean saveRole(@RequestParam int id){
        return datasetService.delete(id);
    }


}
