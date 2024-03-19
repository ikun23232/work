package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.sql.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:43
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "productlstq")
public class Product {
    @Id
    private Integer id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Double)
    private double price;
    @Field(type = FieldType.Integer)
    private Integer stock;
    @Field(type = FieldType.Integer)
    private Integer categoryLevelId;
    @Field(type = FieldType.Integer)
    private Integer fileId;
    @Field(type = FieldType.Integer)
    private Integer isDel;
    @Transient
    private Date createTime;
    @Field(type = FieldType.Text)
    private String filePath;
    @Field(type = FieldType.Text)
    private String brandName;

}
