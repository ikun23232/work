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
import java.sql.Timestamp;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:43
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "productlstaaa")
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
    private Integer categoryId;
    @Field(type = FieldType.Integer)
    private Integer fileId;
    @Field(type = FieldType.Integer)
    private Integer isDel;
    @Transient
    private Timestamp createTime;
    @Field(type = FieldType.Text)
    //增加的
    private String filePath;
    @Field(type = FieldType.Text)
    private String brandName;
    @Field(type = FieldType.Integer)
    private Integer quantity;
    @Field(type = FieldType.Double)
    private Double cost;
    @Field(type = FieldType.Integer)
    private Integer brandId;
    @Field(type = FieldType.Boolean)
    private Boolean concernFalg;
    @Field(type = FieldType.Integer)
    private Integer categoryLevelId;

}
