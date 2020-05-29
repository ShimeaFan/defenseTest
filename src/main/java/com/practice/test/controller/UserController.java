package com.practice.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.result.UpdateResult;
import com.practice.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author fanShiHao
 * @date 2020/5/8 13:44
 * @description
 */
@RestController
@Api(description = "用户操作接口")
@Controller("user")
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @ApiOperation(value = "swagger2 示例 接口", notes="这里是notes信息",httpMethod = "POST")
//  @ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
  @ResponseBody
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
