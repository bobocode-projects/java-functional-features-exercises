package com.bobocode;

import com.bobocode.model.Account;
import com.bobocode.model.Sex;

import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

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
        return accounts.stream()
                .max(Comparator.comparing(Account::getBalance)).get();
    }

    public List<Account> findAccountsByBirthdayMonth(Month birthdayMonth) {
        return accounts.stream()
                .filter(a -> a.getBirthday().getMonth().equals(birthdayMonth))
                .collect(toList());
    }

    public Map<Boolean, List<Account>> partitionMaleAccounts() {
        return accounts.stream()
                .collect(partitioningBy(a->a.getSex().equals(Sex.MALE)));
    }

    public Map<String, List<Account>> groupAccountsByEmailDomain() {
        return accounts.stream()
                .collect(groupingBy(a -> a.getEmail().split("@")[1]));
    }


}
