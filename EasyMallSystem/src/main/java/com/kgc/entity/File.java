package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/22/ 18:47
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class File {
    private int id;
    private int filePath;
    private int productId;
    private int userId;
    private int createTime;
    private int isDelete;
}
