package com.practice.test.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author fanShiHao
 * @date 2020/5/25 17:22
 * @description 使用okhttp3进行http请求
 */
@Slf4j
public class Http {

  /** 未设置超时 */
  private static OkHttpClient client = new OkHttpClient().newBuilder().build();

  /**
   * okhttp设置超时
   */
  private static OkHttpClient clientSite =
      new OkHttpClient()
          .newBuilder()
          // 设置连接超时时间
          .connectTimeout(60, TimeUnit.SECONDS)
          // 设置读取超时时间
          .readTimeout(60, TimeUnit.SECONDS)
          .build();
  /**
   * POST 请求 同步方式
   *
   * @param media 传递方式 "text/plain" "application/json"等
   *     当此值为"application/json,application/json"形式时，headerValue中需添加多次
   * @param content body中的参数，可以为空
   * @param url 请求路径
   * @param method 请求方式 常用的有:POST GET PUT DELETE
   * @param headerValue 请求头值，一般和media一致
   */
  public void httpRequest(
      String media, String content, String url, String method, String headerValue) {
    try {
      MediaType mediaType = MediaType.parse(media);
      RequestBody body = RequestBody.create(mediaType, content);
      Request request =
          new Request.Builder()
              .url(url)
              .method(method, body)
              .addHeader("version", "V1.0.0")
              .addHeader("Content-Type", headerValue)
              .build();
      log.info("------------");
      log.info("开始执行Http请求...");
      Response response = client.newCall(request).execute();
      log.info("Http请求执行完成...");
      log.info("------------");
      log.info("code:" + response.code());
      log.info("message:" + response.message());
      // 关闭Response，避免资源泄露
      response.close();
      Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);
    } catch (IOException e) {
      log.info("------------");
      log.info("Http请求出现异常:" + e.toString());
    }
  }

  /**
   * 异步
   * 在同步的基础上讲execute改成enqueue，并且传入回调接口，但接口回调回来的代码是在非UI线程的，因此如果有更新UI的操作必须切到主线程。
   * */
  public void httpRequestAsy(String media, String content, String url, String method, String headerValue) {
    MediaType mediaType = MediaType.parse(media);
    RequestBody body = RequestBody.create(mediaType, content);
    Request request =
        new Request.Builder()
            .url(url)
            .method(method, body)
            .addHeader("version", "V1.0.0")
            .addHeader("Content-Type", headerValue)
            .build();
    log.info("------------");
    log.info("开始执行Http请求...");
    client
        .newCall(request)
        .enqueue(
            new Callback() {
              @Override
              public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.info("------------");
                log.info("Http请求出现异常:" + e.toString());
              }

              @Override
              public void onResponse(@NotNull Call call, @NotNull Response response)
                  throws IOException {
                if (response.isSuccessful()) {
                  log.info("code:" + response.code());
                  log.info("message:" + response.message());
                  response.close();
                }
                response.close();
              }
            });
    log.info("Http请求执行完成...");
    log.info("------------");
    Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);
  }

  /*常用请求示例 ↓ */

  /**
   * POST 请求 mediaType 为单个时
   *
   * @throws IOException
   */
  public void httpPostOne() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json");
    RequestBody body =
        RequestBody.create(
            mediaType,
            "{\n" + "    \"name\": \"the newcomer\",\n" + "    \"adderss\": \"Provence\"}");
    Request request =
        new Request.Builder()
            .url("http://10.16.105.107:1106/user/update")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
    Response response = client.newCall(request).execute();
  }

  /**
   * POST 请求 mediaType 为多个时
   *
   * @throws IOException
   */
  public void httpPostTwo() throws IOException {
    OkHttpClient client = new OkHttpClient().newBuilder().build();
    MediaType mediaType = MediaType.parse("application/json,application/json");
    RequestBody body =
        RequestBody.create(mediaType, "{\n    \"id\": 654321,\n    \"name\": \"最新测试2\"\n}");
    Request request =
        new Request.Builder()
            .url("http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/save")
            .method("POST", body)
            .addHeader("version", "V1.0.0")
            .addHeader("Content-Type", "application/json")
            .addHeader("Content-Type", "application/json")
            .build();
    Response response = client.newCall(request).execute();
  }

  /** DELETE 请求 参数在body里的 */
  public void httpDelete() {
    try {
      OkHttpClient client = new OkHttpClient().newBuilder().build();
      MediaType mediaType = MediaType.parse("application/json,application/json");
      RequestBody body = RequestBody.create(mediaType, "[23,24]");
      Request request =
          new Request.Builder()
              .url("http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/deleteGroup")
              .method("DELETE", body)
              .addHeader("version", "V1.0.0")
              .addHeader("Content-Type", "application/json")
              .addHeader("Content-Type", "application/json")
              .build();
      Response response = client.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** DELETE 请求 参数为拼接路径的形式 */
  public void httpDeletePath() {
    try {
      OkHttpClient client = new OkHttpClient().newBuilder().build();
      MediaType mediaType = MediaType.parse("text/plain");
      RequestBody body = RequestBody.create(mediaType, "");
      Request request =
          new Request.Builder()
              .url("http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/delete?id=23")
              .method("DELETE", body)
              .addHeader("version", "V1.0.0")
              .build();
      Response response = client.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** GET 请求 */
  public void httpGetPath() {
    try {
      OkHttpClient client = new OkHttpClient().newBuilder().build();
      Request request =
          new Request.Builder()
              .url("http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/findById?id=654321")
              .method("GET", null)
              .addHeader("version", "V1.0.0")
              .addHeader("Content-Type", "application/json")
              .build();
      Response response = client.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  /** PUT 请求 */
  public void httpPutPath() {
    try {
      OkHttpClient client = new OkHttpClient().newBuilder().build();
      MediaType mediaType = MediaType.parse("application/json,application/json");
      RequestBody body =
          RequestBody.create(mediaType, "{\n    \"id\": 654321,\n    \"name\": \"测试00002\"\n}");
      Request request =
          new Request.Builder()
              .url("http://10.16.105.107:8887/api/open/V1.0.0/vectorMarkInfo/updateById")
              .method("PUT", body)
              .addHeader("version", "V1.0.0")
              .addHeader("Content-Type", "application/json")
              .addHeader("Content-Type", "application/json")
              .build();
      Response response = client.newCall(request).execute();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void xx() {
    try {
      OkHttpClient client = new OkHttpClient().newBuilder().build();
      MediaType mediatype = MediaType.parse("application/json");
      RequestBody body = RequestBody.create(mediatype, "content");
      Request request =
          new Request.Builder()
              .url("http://10.16.105.13")
              .method("PUT", body)
              .addHeader("version", "V1.0.0")
              .addHeader("Content-type", "application/json")
              .build();
      Response response = client.newCall(request).execute();
    } catch (IOException e) {
      log.info(e.toString());
    }
  }
}
