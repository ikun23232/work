package com.kgc.utils;

import com.kgc.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:33
 **/
public interface ElsearchUtil extends ElasticsearchRepository<Product, String> {
}
