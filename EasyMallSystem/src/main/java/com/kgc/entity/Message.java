package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/17/ 18:12
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String code;
    private String message;
    private Object data;

    public static Message success() {
        return new Message("200", "操作成功", null);
    }

    public static Message success(Object data) {
        return new Message("200", "操作成功", data);
    }

    public static Message error() {
        return new Message("201", "操作失败", null);
    }

    public static Message error(String message) {
        return new Message("201", message, null);
    }
}
