package me.coding.hikari.controller;

import lombok.extern.slf4j.Slf4j;
import me.coding.hikari.entity.Account;
import me.coding.hikari.repository.AccountRepository;
import me.coding.hikari.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@RestController
@Slf4j
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/{accountId}")
    @Transactional
    public Account findById(@PathVariable  Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.isPresent() ? account.get() : null;
    }

    @GetMapping(value = "/test")
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {

                }
                accountService.findById(1);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        countDownLatch.countDown();
    }
}
