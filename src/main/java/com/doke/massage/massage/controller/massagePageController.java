package com.doke.massage.massage.controller;

import com.doke.massage.massage.pojo.Massage.Acupoint;
import com.doke.massage.massage.pojo.Massage.BodyPart;
import com.doke.massage.massage.pojo.Massage.massageMethod;
import com.doke.massage.massage.service.massagePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class massagePageController {
    @Autowired
    private massagePageService massagePageService;//获取按摩区按摩方法service

    /**
     * 获取按摩区按摩方法
     * @return 按摩方法的list
     */
    @GetMapping("/getMassageMethods")
    public List<massageMethod> getMassageMethods(){
        List<massageMethod> methods = massagePageService.getMassageMethods();
        return methods;
    }

    /**
     * 获取身体部位方法
     * @return 身体部位的List
     */
    @GetMapping("/getBodyPart")
    public List<BodyPart> getBodyPart(){
        return massagePageService.getBodyPart();
    }

    @GetMapping("/getAcupoint")
    public List<Acupoint> getAcupoint(Integer bodyPartId){
        return massagePageService.getAcupoint(bodyPartId);
    }
}
