package com.example.login_authetic.entity;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * 用户性别枚举.
 *
 */
@Converter(autoApply = true)
public enum UserGender {
    MAN(0, "男"), WOMEN(1, "女"), UNKNOWN(2, "未知性别"), SECRET(3, "保密性别");

    private int value;
    private String desc;

    UserGender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class Convert implements AttributeConverter<UserGender, Integer> {
//       在写入数据库时，jpa会调用convert的convertToDatabaseColumn方法
        @Override
        public Integer convertToDatabaseColumn(UserGender attribute) {
            return attribute == null ? null : attribute.getValue();
        }

//      在反向查询时，通过数据库的值和遍历的枚举的value进行比较，然后返回UserAction实体
        @Override
        public UserGender convertToEntityAttribute(Integer dbData) {
            for (UserGender type : UserGender.values()) { //将数字转换为描述
                if (dbData.equals(type.getValue())) {
                    return type;
                }
            }
            throw new RuntimeException("Unknown database value: " + dbData);
        }
    }
}


