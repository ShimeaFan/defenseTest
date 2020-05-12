package com.practice.test.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.practice.test.entity.UserEntity;
import com.practice.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author fanShiHao
 * @date 2020/5/8 13:39
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String save(JSONObject jsonObject) {
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            UserEntity user = JSON.toJavaObject(data, UserEntity.class);
            mongoTemplate.save(data);
            return "yes";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "no";
    }
}
