package com.doke.massage.massage.service;

import com.doke.massage.massage.dao.attention_dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class attentionService {
    @Autowired
    private attention_dao attentionDao;

    public String get_attention(){
        String attention = attentionDao.query();
        return attention;
    }
}
