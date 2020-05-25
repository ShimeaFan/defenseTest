package com.practice.test.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import com.practice.test.entity.UserEntity;
import com.practice.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanShiHao
 * @date 2020/5/8 13:39
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired MongoTemplate mongoTemplate;

  @Override
  public String save(JSONObject jsonObject) {
    try {
      JSONObject data = jsonObject.getJSONObject("data");
      if (data != null) {
        UserEntity user = JSON.toJavaObject(data, UserEntity.class);
        mongoTemplate.save(user);
        return "yes";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "no";
  }

  @Override
  public List<UserEntity> find(UserEntity entity) {
    List<UserEntity> list = new ArrayList<>();
    Criteria criteria = new Criteria();
    Query query = new Query();

    try {
      if (StringUtils.isEmpty(entity.getName())) {
        criteria.and("name").is(entity.getName());
      }
      if (StringUtils.isEmpty(entity.getAddress())) {
        criteria.and("address").is(entity.getAddress());
      }
      if (StringUtils.isEmpty(entity.getName())) {
        criteria.and("name").is(entity.getName());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 遍历实体 进行修改
   * 省去update.set("name", t.getName());逐个添加实体中的参数
   * @param dbObject
   * @return
   */
  @Override
  public UpdateResult ss(DBObject dbObject) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(dbObject.get("name")));
    Update update = new Update();
    for (String key : dbObject.keySet()) {
      Object value = dbObject.get(key);
      if (value != null) {
        update.set(key, value);
      }
    }
      return mongoTemplate.upsert(query, update, UserEntity.class);
  }
}
