package me.coding.seata.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.coding.seata.account.entity.Account;
import me.coding.seata.account.mapper.AccountMapper;
import me.coding.seata.api.service.AccountService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @PostConstruct
    private void init() {
        Account account = accountMapper.getById(1);
        System.out.println("=========================" + account);
    }

    @Override
    public void debit(String userId, int money) {
        log.info("AccountService debit");
    }
}
