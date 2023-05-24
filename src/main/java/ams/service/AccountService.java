package ams.service;

import ams.model.entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findByAccount(String account);
}
