package com.source.code.springAOP2.dao;

import com.source.code.springAOP2.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ProductDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    public void createOrder(Product product){
        String sql = "update product set num = num - ? where id = ?";
        jdbcTemplate.update(sql,product.getNum(),product.getId());
    };
}
