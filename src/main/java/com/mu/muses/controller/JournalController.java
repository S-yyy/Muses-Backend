package com.mu.muses.controller;

import com.mu.muses.config.RestResponse;
import com.mu.muses.service.JournalService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Api(tags = "科研工具箱")
public class JournalController {
    @Autowired
    JournalService journalService;

    @Operation(summary = "文献检索")
    @CrossOrigin
    @PostMapping(value = "/api/toolbox/papers")
    @ResponseBody
    public RestResponse getPaper(@RequestBody Map map){
        int pageNo = (int) map.get("pageNo");
        int pageSize = (int) map.get("pageSize");
        return RestResponse.success(journalService.findJournalPage(pageNo,pageSize).getContent().toArray());
    }
}
