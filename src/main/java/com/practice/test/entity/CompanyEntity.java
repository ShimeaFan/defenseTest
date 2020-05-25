package com.practice.test.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fanShiHao
 * @date 2020/5/25 15:26
 * @description 公司类
 */
@Data
public class CompanyEntity implements Serializable {

  /** 公司名称 */
  private String companyName;

  /** 公司收入 */
  private int companyIncome;
}
