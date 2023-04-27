package com.mu.muses.controller;

import com.mu.muses.config.RestResponse;
import com.mu.muses.enums.ResultCode;
import com.mu.muses.dto.CaseQuery;
import com.mu.muses.dto.DatabaseQuery;
import com.mu.muses.dto.Response;
import com.mu.muses.entity.CaseData;
import com.mu.muses.entity.Dataset;
import com.mu.muses.service.CaseDataService;
import com.mu.muses.service.DatasetService;
import com.mu.muses.service.EnumsService;
import com.mu.muses.service.ResearchService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "多模态信息库")
public class CaseDataController {
    @Autowired
    CaseDataService caseDataService;
    @Autowired
    EnumsService enumsService;
    @Autowired
    DatasetService datasetService;
    @Autowired
    ResearchService researchService;

    @Operation(summary = "查询病例")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases")
    @ResponseBody
    public RestResponse findCaseData(@RequestBody CaseQuery caseQuery){
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
        return RestResponse.success(response);
    }


    @Operation(summary = "录入病例")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases/saveCaseData")
    @ResponseBody
    public RestResponse saveCaseData(@RequestBody CaseData caseData){
        CaseData data = caseDataService.save(caseData);
        if (data!= null)
            return RestResponse.success();
        else
            return RestResponse.fail(ResultCode.FAIL_UPDATE);
    }

    @Operation(summary = "批量录入病例")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases/saveCaseDatas")
    @ResponseBody
    public RestResponse saveCaseDatas(@RequestBody List<CaseData> caseDatas){
        if(caseDataService.saveAll(caseDatas)){
            return RestResponse.success();
        }
        else {
            return RestResponse.fail(ResultCode.FAIL_UPDATE);
        }
    }

    @Operation(summary = "删除病例")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/cases/deleteCaseData")
    @ResponseBody
    public RestResponse deleteCaseData(@RequestParam int id){
        if(caseDataService.findById(id)==null){
            return RestResponse.fail(ResultCode.FAIL_DELETE);
        }
        else {
            caseDataService.deleteByID(id);
            return RestResponse.success();
        }
    }

    @Operation(summary = "批量删除病例")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/cases/deleteCaseDatas")
    @ResponseBody
    public RestResponse deleteCaseDatas(@RequestBody List<Integer> id){
        for (int item : id){
            if(caseDataService.findById(item)==null){
                return RestResponse.fail(ResultCode.FAIL_DELETE);
            }
        else {
                caseDataService.deleteByID(item);
            }
        }
        return RestResponse.success();

    }

    @Operation(summary = "查询患者所有时间节点病例列表")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases/patients")
    @ResponseBody
    public RestResponse getCaseDatas(@RequestParam int patientId){
        return RestResponse.success(caseDataService.findByPatientId(patientId));
    }

    @Operation(summary = "查询患者某个时间点病例列表")
    @CrossOrigin
    @PostMapping(value = "/api/database/cases/patient")
    @ResponseBody
    public RestResponse getPatientCaseData(@RequestParam int patientId,@RequestParam String visitDate){
        return RestResponse.success(caseDataService.findByPatientIdAndDate(patientId, visitDate));
    }


    @Operation(summary = "查询诊断类型枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/treatmentType/enums")
    @ResponseBody
    public RestResponse getTreatmentTypeEnums(){
        return RestResponse.success(enumsService.findAllByTreatmentType());
    }

    @Operation(summary = "查询医疗影像类型枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/medicalImages/enums")
    @ResponseBody
    public RestResponse getMedicalImagesEnums(){
        return RestResponse.success(enumsService.findMedicalImagesEnums());
    }


    @Operation(summary = "查询体部枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/bodyParts/enums")
    @ResponseBody
    public RestResponse getBodyPartsEnums(){
        return RestResponse.success(enumsService.findBodyPartsEnums());
    }


    @Operation(summary = "查询疾病类型枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/illness/enums")
    @ResponseBody
    public RestResponse getIllnessEnums(){
        return RestResponse.success(enumsService.findIllnessEnums());
    }


    @Operation(summary = "查询疾病亚型枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/illnessSubtype/enums")
    @ResponseBody
    public RestResponse getIllnessSubtypeEnums(){
        return RestResponse.success(enumsService.findIllnessSubtypeEnums());
    }

    @Operation(summary = "查询科室枚举")
    @CrossOrigin
    @GetMapping(value = "/api/database/cases/medicalSection/enums")
    @ResponseBody
    public RestResponse getMedicalSection(){
        return RestResponse.success(enumsService.findMedicalSectionEnums());
    }


    @Operation(summary = "查询数据集列表")
    @CrossOrigin
    @PostMapping(value = "/api/database/datasets")
    @ResponseBody
    public RestResponse findDatasets(@RequestBody DatabaseQuery databaseQuery){
        Response response = new Response();
        Page<Dataset> page = datasetService.findDash(databaseQuery);
        response.data = page.getContent().toArray();
        response.total = (int) page.getTotalElements();
        response.pageSize = page.getTotalPages();
        response.pageNum = databaseQuery.pageNo;
        return RestResponse.success(response);
    }

    @Operation(summary = "保存或更新数据集")
    @CrossOrigin
    @PostMapping(value = "/api/database/datasets/saveDataset")
    @ResponseBody
    public RestResponse saveRole(@RequestBody Dataset dataset){
        dataset.name = "数据集"+dataset.name;
        if(datasetService.save(dataset)){
            return RestResponse.success();
        }
        else{
            return RestResponse.fail(ResultCode.FAIL_UPDATE);
        }
    }

    @Operation(summary = "添加病例数据到数据集")
    @CrossOrigin
    @PostMapping(value = "/api/database/datasets/addCaseDatas")
    @ResponseBody
    public RestResponse saveRole(@RequestBody Map<String,Object> map){
        Dataset dataset = datasetService.findById((Integer) map.get("id"));
        List<Integer> cases = dataset.getCases();
        List<Integer> newCases = (List<Integer>) map.get("cases");

        newCases.removeAll(cases);
        if(!newCases.isEmpty()) {
            cases.addAll(newCases);
        }

        dataset.setCases(cases);
        datasetService.save(dataset);
        return RestResponse.success();
    }


    @Operation(summary = "删除数据集")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/datasets/deleteDataset")
    @ResponseBody
    public RestResponse saveRole(@RequestParam int id){
        if (datasetService.findById(id)==null){
            return RestResponse.fail(ResultCode.FAIL_DELETE);
        }
        return RestResponse.success();
    }

    @Operation(summary = "批量删除数据集")
    @CrossOrigin
    @DeleteMapping(value = "/api/database/cases/deleteDatasets")
    @ResponseBody
    public RestResponse deleteDatasets(@RequestBody List<Integer> id){
        for (int item : id){
            if(datasetService.findById(item)==null){
                return RestResponse.fail(ResultCode.FAIL_DELETE);
            }
            else {
                datasetService.delete(item);
            }
        }
            return RestResponse.success();
    }

}
