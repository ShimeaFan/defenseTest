package com.practice.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import com.practice.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanShiHao
 * @date 2020/5/8 13:44
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {

  @Autowired private UserService userService;

  @RequestMapping(value = "save", method = RequestMethod.POST)
  public String save(@RequestBody JSONObject data) {
    return userService.save(data);
  }

  @RequestMapping(value = "update", method = RequestMethod.POST)
  public UpdateResult update1(@RequestBody BasicDBObject data) {
    return userService.ss(data);
  }

  @RequestMapping(value = "update2", method = RequestMethod.POST)
  public String update2(@RequestBody JSONObject data) {
    return userService.save(data);
  }
}
