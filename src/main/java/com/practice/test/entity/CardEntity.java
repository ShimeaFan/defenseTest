package com.practice.test.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author fanShiHao
 * @date 2020/5/25 15:23
 * @description
 */
@Data
public class CardEntity implements Serializable {

  @Field("money")
  private int money;

  @Field("bank")
  private String bank;
}
