package com.bobocode;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import com.bobocode.model.Sex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class AccountAnalyticsTest {

    private AccountAnalytics analytics;
    private List<Account> accounts;

    @Before
    public void setUp() {
        accounts = Accounts.getAccountList(10);
        analytics = AccountAnalytics.of(accounts);
    }

    @Test
    public void testGetRichestPerson() {
        Account richestPerson = analytics.getRichestPerson();

        assertEquals(getRichestPerson(), richestPerson);
    }

    private Account getRichestPerson() {
        Account richestPerson = accounts.get(0);
        for (Account account : accounts) {
            if (account.getBalance().compareTo(richestPerson.getBalance()) > 0) {
                richestPerson = account;
            }
        }

        return richestPerson;
    }

    @Test
    public void testSeparateMaleAccounts() {
        Map<Boolean, List<Account>> maleToAccountsMap = analytics.partitionMaleAccounts();

        assertEquals(partitionMaleAccounts(), maleToAccountsMap);
    }

    private Map<Boolean, List<Account>> partitionMaleAccounts() {
        Map<Boolean, List<Account>> maleToAccountsMap = initializePartitionMap();

        for (Account account : accounts) {
            if (account.getSex().equals(Sex.MALE)) {
                maleToAccountsMap.get(true).add(account);
            } else {
                maleToAccountsMap.get(false).add(account);
            }
        }

        return maleToAccountsMap;
    }

    private Map<Boolean, List<Account>> initializePartitionMap() {
        Map<Boolean, List<Account>> partitionMap = new HashMap<>();
        partitionMap.put(true, new ArrayList<>());
        partitionMap.put(false, new ArrayList<>());
        return partitionMap;
    }

    @Test
    public void testFindAccountsByBirthdayMonth() {
        List<Account> aprilAccounts = analytics.findAccountsByBirthdayMonth(Month.APRIL);

        assertEquals(findAccountsByBirthdayMonth(Month.APRIL), aprilAccounts);
    }

    private List<Account> findAccountsByBirthdayMonth(Month month) {
        List<Account> accountList = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getBirthday().getMonth().equals(month)) {
                accountList.add(account);
            }
        }

        return accountList;
    }

    @Test
    public void testGroupAccountsByEmailDomain() {
        Map<String, List<Account>> emailDomainToAccountsMap = analytics.groupAccountsByEmailDomain();

        assertEquals(groupAccountsByEmailDomain(), emailDomainToAccountsMap);
    }

    private Map<String, List<Account>> groupAccountsByEmailDomain() {
        Map<String, List<Account>> emailToAccountsMao = new HashMap<>();

        for (Account account : accounts) {
            String emailDomain = account.getEmail().split("@")[1];
            if (emailToAccountsMao.containsKey(emailDomain)) {
                emailToAccountsMao.get(emailDomain).add(account);
            } else {
                List<Account> accountList = new ArrayList<>();
                accountList.add(account);
                emailToAccountsMao.put(emailDomain, accountList);
            }
        }

        return emailToAccountsMao;
    }
}

