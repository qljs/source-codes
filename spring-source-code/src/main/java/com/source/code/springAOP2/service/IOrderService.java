package com.source.code.springAOP2.service;

import com.source.code.springAOP2.domain.Product;

public interface IOrderService {

    void createOrder(Product product);

    void createOrder2(Product product);
}
