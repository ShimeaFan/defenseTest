package com.practice.test.service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import com.practice.test.entity.UserEntity;

import java.util.List;

/**
 * @author fanShiHao
 * @date 2020/5/8 10:50
 * @description
 */
public interface UserService {
  /**
   * save
   *
   * @param jsonObject
   * @return
   */
  String save(JSONObject jsonObject);

  /**
   * 查询 可以根据字段实现get请求
   *
   * @param entity
   * @return
   */
  List<UserEntity> find(UserEntity entity);
  /**
   * 查询 可以根据字段实现get请求
   *
   * @param entity
   * @return
   */
  UpdateResult ss(DBObject entity);
}
