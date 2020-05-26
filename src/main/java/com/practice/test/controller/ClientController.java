package com.practice.test.controller;

import com.practice.test.util.Http;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author fanShiHao
 * @date 2020/5/26 11:32
 * @description
 */
@RestController
@RequestMapping(value = "client")
@Slf4j
public class ClientController {

  @RequestMapping(value = "test", method = RequestMethod.POST)
  public void test(HttpServletResponse response) {
    String url = "http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/save";
    String content = "{\n" + "    \"id\": 65564479,\n" + "    \"name\": \"okhttp3\"\n" + "}";
    String media = "application/json";
    String method = "POST";
    String headerValue = "application/json";
    Http http = new Http();
    http.httpRequest(media, content, url, method, headerValue);
    log.info("maybe is ok...");
  }

  @RequestMapping(value = "test2", method = RequestMethod.DELETE)
  public void test2(HttpServletResponse response) {
    String url = "http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/deleteGroup";
    String content = "[65564479]";
    String media = "application/json";
    String method = "DELETE";
    String headerValue = "application/json";
    Http http = new Http();
    http.httpRequest(media, content, url, method, headerValue);
    log.info("maybe is ok...");
  }
}
