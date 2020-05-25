package com.practice.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author fanShiHao
 * @date 2020/5/8 10:04
 * @description 用户实体
 */
@Data
@Document(collection = "user")
public class UserEntity extends SuperEntity implements Serializable {

  @Id private String id;

  @Field("name")
  private String name;

  @Field("age")
  private int age;

  @Field("address")
  private String address;

  @Field("dou")
  private double dou;

  @Field("lo")
  private long lo;

  @Field("sh")
  private short sh;

  @Field("by")
  private byte by;

  @Field("ch")
  private char ch;

  @Field("boo")
  private boolean boo;

  @Field("date")
  private Date date;

    /**
     * 员工相关证件
     */
  @Field("card")
  private CardEntity card;

    /**
     * 对接公司
     */
  @Field("company")
  private List<CompanyEntity> company;
}
