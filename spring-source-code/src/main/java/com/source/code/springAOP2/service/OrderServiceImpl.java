package com.source.code.springAOP2.service;

import com.source.code.springAOP2.dao.AccountDao;
import com.source.code.springAOP2.dao.ProductDao;
import com.source.code.springAOP2.domain.Account;
import com.source.code.springAOP2.domain.Product;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements IOrderService {


    @Resource
    private ProductDao productDao;

    @Resource
    private AccountDao accountDao;

    @Transactional
    public void createOrder(Product product) {
        productDao.createOrder(product);
        System.out.println("======create order start =======");
        Account account = new Account(1,new BigDecimal(10));
        accountDao.updateAccount(account);

     //   ((IOrderService)AopContext.currentProxy()).createOrder2(new Product(1,"ce",10));

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createOrder2(Product product) {
        productDao.createOrder(product);
        System.out.println("======create order start =======");
        Account account = new Account(1,new BigDecimal(10));
        accountDao.updateAccount(account);
    }

}
