package com.source.code.springAOP2.domain;


import java.math.BigDecimal;

public class Account {

    private Integer id;

    private BigDecimal balance;

    public  Account(Integer id, BigDecimal balance){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
