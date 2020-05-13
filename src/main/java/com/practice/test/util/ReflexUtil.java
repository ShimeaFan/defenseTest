package com.practice.test.util;

import com.practice.test.entity.UserEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author fanShiHao
 * @date 2020/5/13 14:38
 * @description JAVA 字段反射（https://www.w3cschool.cn/java/java-field-reflection.html）
 */
public class ReflexUtil {

    public static void main(String[] args) {
        Class<UserEntity> c = UserEntity.class;
        ArrayList<String> fieldsDescription =getDeclaredFieldsList(c);

        System.out.println("Declared  Fields  for " + c.getName());


        //循环输出 Class 中的 每个字段属性
        for(String s:fieldsDescription){
            System.out.println(s);
        }


        fieldsDescription = getFieldsList(c);

        System.out.println("\nAccessible Fields for " + c.getName());
        for (String desc : fieldsDescription) {
            System.out.println(desc);
        }
    }

    /**
     * 获取一个类的所有字段属性列表
     *
     * @param c 一个实体类
     * @return 此实体类的字段的信息（修饰符+字段类型+字段名称）
     */
    public static ArrayList<String> getFieldsList(Class c) {
        Field[] fields = c.getFields();
        ArrayList<String> arrayList = getFieldsDescription(fields);
        return arrayList;
    }

    public static ArrayList<String> getDeclaredFieldsList(Class c) {
        Field[] fields = c.getDeclaredFields();
        ArrayList<String> arrayList = getFieldsDescription(fields);
        return arrayList;
    }


    /**
     * 获取 一个类的 修饰符 + 字段类型 + 字段名称
     *
     * @param fields 字节数组
     * @return 一个类的所有的字段
     */
    public static ArrayList<String> getFieldsDescription(Field[] fields) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Field field : fields) {
            int mod = field.getModifiers() & Modifier.fieldModifiers();
            String modifier = Modifier.toString(mod);
            Class<?> type = field.getType();
            String typename = type.getSimpleName();
            String fieldName = field.getName();
            arrayList.add(modifier + " " + typename + " " + fieldName);
        }
        return arrayList;
    }
}
