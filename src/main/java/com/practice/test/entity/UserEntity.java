package com.practice.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author fanShiHao
 * @date 2020/5/8 10:04
 * @description 用户实体
 */
@Data
@Document(collection = "user")
public class UserEntity implements Serializable {

    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("age")
    private int age;
    @Field("address")
    private String address;

}
