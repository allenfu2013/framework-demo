package me.coding.hikari.service;

import me.coding.hikari.entity.Account;
import me.coding.hikari.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public Account findById(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        try {
            Thread.sleep(3050);
        } catch (InterruptedException e) {

        }
        return account.isPresent() ? account.get() : null;
    }
}
