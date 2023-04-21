package com.mu.muses.controller;

import com.mu.muses.config.RestResponse;
import com.mu.muses.config.ResultCode;
import com.mu.muses.dto.Response;
import com.mu.muses.entity.Research;
import com.mu.muses.service.ResearchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ResearchController {
    @Autowired
    ResearchService researchService;

    @Operation(summary = "课题进度预览")
    @CrossOrigin
    @GetMapping(value = "/api/research/topics/status")
    @ResponseBody
    public RestResponse getTopicAll() {
        return RestResponse.success(researchService.findAll());
    }

    @Operation(summary = "课题进度预览")
    @CrossOrigin
    @GetMapping(value = "/api/research/topics/dist/status")
    @ResponseBody
    public RestResponse getTopicDistStatus() {
        return RestResponse.success(researchService.findStatus());
    }


    @Operation(summary = "保存或更新课题数据")
    @CrossOrigin
    @PostMapping(value = "/api/research/topics/saveTopic")
    @ResponseBody
    public RestResponse saveTopic(@RequestBody Research research) {
        if(!researchService.save(research)){
            return RestResponse.fail(ResultCode.FAIL_UPDATE);
        }
        return RestResponse.success();
    }

    @Operation(summary = "查询课题列表")
    @CrossOrigin
    @GetMapping(value = "/api/research/topics")
    @ResponseBody
    public RestResponse getTopic(@RequestParam int pageNo, @RequestParam int pageSize) {
        Response response = new Response();
        Page<Research> page =  researchService.findAll(pageNo,pageSize);
        response.data = page.getContent().toArray();
        response.total = (int) page.getTotalElements();
        response.pageSize = page.getTotalPages();
        response.pageNum = pageNo;
        return RestResponse.success(response);
    }

    @Operation(summary = "查询课题详情")
    @CrossOrigin
    @GetMapping(value = "/api/research/topics/topic")
    @ResponseBody
    public RestResponse getTopic(@RequestParam int id) {
        return RestResponse.success(researchService.findById(id));
    }

    @Operation(summary = "删除课题")
    @CrossOrigin
    @DeleteMapping(value = "/api/research/topics/deleteTopic")
    @ResponseBody
    public RestResponse deleteTopic(@RequestParam int id) {
        if (researchService.findById(id)==null){
            return RestResponse.fail(ResultCode.FAIL_DELETE);
        }
        else{
            researchService.delete(id);
            return RestResponse.success();
        }

    }

    @Operation(summary = "批量删除课题")
    @CrossOrigin
    @DeleteMapping(value = "/api/research/topics/deleteTopics")
    @ResponseBody
    public RestResponse deleteTopics(@RequestParam List<Integer> id) {
        for( int item :id){
            if (researchService.findById(item)==null){
                return RestResponse.fail(ResultCode.FAIL_DELETE);
            }
            else{
                researchService.delete(item);
            }
        }
        return RestResponse.success();
    }

    @Operation(summary = "查询课题状态枚举")
    @CrossOrigin
    @GetMapping(value = "/api/topics/status/enums")
    @ResponseBody
    public RestResponse getStatusEnums() {
        return RestResponse.success(getTopicDistStatus());
    }

}
