package com.doke.massage.massage.service;

import com.doke.massage.massage.dao.Massage.AcupointDao;
import com.doke.massage.massage.dao.Massage.bodyPartDao;
import com.doke.massage.massage.dao.Massage.massageMethodDao;
import com.doke.massage.massage.pojo.Massage.Acupoint;
import com.doke.massage.massage.pojo.Massage.BodyPart;
import com.doke.massage.massage.pojo.Massage.massageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 按摩区页面内容获取类
 */
@Service
public class massagePageService {
    @Autowired
    private massageMethodDao massageMethodDao;//获取按摩方法dao

    @Autowired
    private bodyPartDao bodyPartDao;//身体部位Dao

    @Autowired
    private AcupointDao acupointDao;
    /**
     * 获取按摩方法Service
     * @return 按摩方法List
     */
    public List<massageMethod> getMassageMethods(){
        List<massageMethod> methods = massageMethodDao.getMethod();
        return methods;
    }

    /**
     * 获取身体部位Service
     * @return 身体部位List
     */
    public List<BodyPart> getBodyPart(){
        return bodyPartDao.findAll();
    }

    public List<Acupoint> getAcupoint(Integer bodyPartId){
        return acupointDao.findAcupoint(bodyPartId);
    }
}
