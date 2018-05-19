package com.bobocode;

import com.bobocode.model.Account;

import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Implement methods using Stream API
 */
public class AccountAnalytics {
    private Collection<Account> accounts;

    public static AccountAnalytics of(Collection<Account> accounts) {
        return new AccountAnalytics(accounts);
    }

    private AccountAnalytics(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getRichestPerson() {
        throw new UnsupportedOperationException("It's your job to make it work!"); // todo
    }

    public List<Account> findAccountsByBirthdayMonth(Month birthdayMonth) {
        throw new UnsupportedOperationException("It's your job to make it work!"); // todo
    }

    public Map<Boolean, List<Account>> partitionMaleAccounts() {
        throw new UnsupportedOperationException("It's your job to make it work!"); // todo
    }

    public Map<String, List<Account>> groupAccountsByEmailDomain() {
        throw new UnsupportedOperationException("It's your job to make it work!"); // todo
    }


}
