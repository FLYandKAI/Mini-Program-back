package com.doke.massage.massage.controller;

import com.doke.massage.massage.service.attentionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ResponseBody
public class attentionController {
    @Autowired
    private attentionService attentionService;
    @ApiOperation(value = "获取注意事项")
    @GetMapping("/attention")
    public String attention(){
        String attention = attentionService.get_attention();
        return attention;
    }
}

