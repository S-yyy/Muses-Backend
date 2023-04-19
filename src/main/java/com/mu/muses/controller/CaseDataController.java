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
import io.swagger.models.auth.In;
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
    @CrossOrigin
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

    @Operation(summary = "查询病例")
    @CrossOrigin
    @PostMapping(value = "/api/database/case")
    @ResponseBody
    public CaseData findSingleCase(@RequestParam int id){
        return caseDataService.findById(id);
    }

    @Operation(summary = "录入病例")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases/saveCaseData")
    @ResponseBody
    public Boolean saveCaseData(@RequestBody CaseData caseData){
        return caseDataService.save(caseData);
    }

    @Operation(summary = "批量录入病例")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases/saveCaseDatas")
    @ResponseBody
    public Boolean saveCaseDatas(@RequestBody List<CaseData> caseDatas){
        return caseDataService.saveAll(caseDatas);
    }

    @Operation(summary = "删除病例")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/cases/deletePostData")
    @ResponseBody
    public Boolean deleteCaseData(@RequestParam int id){
        if(caseDataService.findById(id)==null){
            return false;
        }
        else {
            caseDataService.deleteByID(id);
            return true;
        }
    }

    @Operation(summary = "批量删除病例")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/cases/deletePostDatas")
    @ResponseBody
    public Boolean deleteCaseDatas(@RequestBody List<Integer> id){
        Boolean delete = true;
        for (int item : id){
            if(caseDataService.findById(item)==null){
                delete = false;
            }
        else {
                caseDataService.deleteByID(item);
            }
        }
        return delete;
    }

    @Operation(summary = "查询患者所有时间节点病例列表")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/patients")
    @ResponseBody
    public List<CaseData> getCaseDatas(@RequestParam int patientId){
        return caseDataService.findByPatientId(patientId);
    }

    @Operation(summary = "查询患者某个时间点病例列表")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/patient")
    @ResponseBody
    public CaseData getPatientCaseData(@RequestParam int patientId,@RequestParam String visitDate){
        return caseDataService.findByPatientIdAndDate(patientId, visitDate);
    }

    @Operation(summary = "查询诊断类型枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/treatmentType/enums")
    @ResponseBody
    public List<Enums> getTreatmentTypeEnums(){
        return enumsService.findAllByTreatmentType();
    }

    @Operation(summary = "查询数据集列表")
    @CrossOrigin
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
    @CrossOrigin
    @PostMapping(value = "/api/database/datasets/saveDataset")
    @ResponseBody
    public boolean saveRole(@RequestBody Dataset dataset){
        return datasetService.save(dataset);
    }

    @Operation(summary = "删除数据集")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/datasets/deleteDataset")
    @ResponseBody
    public boolean saveRole(@RequestParam int id){
        if (datasetService.findById(id)==null){
            return false;
        }
        return datasetService.delete(id);
    }

    @Operation(summary = "批量删除数据集")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/cases/deleteDatasets")
    @ResponseBody
    public Boolean deleteDatasets(@RequestParam List<Integer> id){
        Boolean delete = true;
        for (int item : id){
            if(datasetService.findById(item)==null){
                delete = false;
            }
            else {
                datasetService.delete(item);
            }
        }
        return delete;
    }

}
