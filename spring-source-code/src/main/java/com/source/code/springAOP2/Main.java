package com.source.code.springAOP2;

import com.source.code.springAOP2.config.JdbcConfig;
import com.source.code.springAOP2.domain.Product;
import com.source.code.springAOP2.service.IOrderService;
import com.source.code.springAOP2.service.OrderServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        IOrderService orderService = context.getBean(IOrderService.class);
        orderService.createOrder(new Product(1,"",10));
    }
}
