package com.practice.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.practice.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody JSONObject data) {
        return userService.save(data);
    }
}
