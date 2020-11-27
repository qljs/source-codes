package com.source.code.springAOP2.dao;

import com.source.code.springAOP2.domain.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountDao {

    @Resource
    JdbcTemplate jdbcTemplate;

    void updateAccount(Account account) {
        String sql = "update account set balance = balance - ? where id=?";
        jdbcTemplate.update(sql,account.getBalance(), account.getId());
    }
}
