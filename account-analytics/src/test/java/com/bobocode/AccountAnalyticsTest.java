package com.bobocode;

import com.bobocode.model.Account;
import com.bobocode.model.Sex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * The helper method of this test class do not use Stream API intentionally. You should try to find a stream-based
 * solutions for {@link AccountAnalytics} by yourself.
 */
@RunWith(JUnit4.class)
public class AccountAnalyticsTest {

    private AccountAnalytics analytics;
    private List<Account> accounts;

    @Before
    public void setUp() {
        accounts = Arrays.asList(
                new Account(1L, "Justin", "Butler", "justin.butler@gmail.com",
                        LocalDate.parse("2003-04-17"), Sex.MALE, LocalDateTime.now(), BigDecimal.valueOf(172966)),
                new Account(1L, "Olivia", "Cardenas", "cardenas@mail.com",
                        LocalDate.parse("1930-01-19"), Sex.FEMALE, LocalDateTime.now(), BigDecimal.valueOf(38029)),
                new Account(1L, "Nolan", "Donovan", "nolandonovan@gmail.com",
                        LocalDate.parse("1925-04-19"), Sex.MALE, LocalDateTime.now(), BigDecimal.valueOf(13889)),
                new Account(1L, "Lucas", "Lynn", "lucas.lynn@yahoo.com",
                        LocalDate.parse("1987-05-25"), Sex.MALE, LocalDateTime.now(), BigDecimal.valueOf(16980))
        );
        analytics = AccountAnalytics.of(accounts);
    }

    @Test
    public void testFindRichestPerson() {
        Optional<Account> expectedPerson = Optional.of(accounts.get(0));
        Optional<Account> actualRichestPerson = analytics.findRichestPerson();

        assertEquals(expectedPerson, actualRichestPerson);
    }

    @Test
    public void testSeparateMaleAccounts() {
        Map<Boolean, List<Account>> expectedAccountMap = getExpectedMaleMap();
        Map<Boolean, List<Account>> maleToAccountsMap = analytics.partitionMaleAccounts();

        assertEquals(expectedAccountMap, maleToAccountsMap);
    }

    private Map<Boolean, List<Account>> getExpectedMaleMap() {
        Map<Boolean, List<Account>> expectedMap = new HashMap<>(2);
        expectedMap.put(Boolean.TRUE, Arrays.asList(accounts.get(0), accounts.get(2), accounts.get(3)));
        expectedMap.put(Boolean.FALSE, Arrays.asList(accounts.get(1)));
        return expectedMap;
    }

    @Test
    public void testFindAccountsByBirthdayMonth() {
        List<Account> expectedList =  getExpectedList();
        List<Account> aprilAccounts = analytics.findAccountsByBirthdayMonth(Month.APRIL);

        assertEquals(expectedList, aprilAccounts);
    }

    private List<Account> getExpectedList() {
        return Arrays.asList(accounts.get(0), accounts.get(2));
    }

    @Test
    public void testGroupAccountsByEmailDomain() {
        Map<String, List<Account>> expectedEmailMap = getExpectedEmailMap();
        Map<String, List<Account>> emailDomainToAccountsMap = analytics.groupAccountsByEmailDomain();

        assertEquals(expectedEmailMap, emailDomainToAccountsMap);
    }

    private Map<String, List<Account>> getExpectedEmailMap() {
        Map<String, List<Account>> expectedEmailMap = new HashMap<>();
        expectedEmailMap.put("gmail.com", Arrays.asList(accounts.get(0), accounts.get(2)));
        expectedEmailMap.put("mail.com", Arrays.asList(accounts.get(1)));
        expectedEmailMap.put("yahoo.com", Arrays.asList(accounts.get(3)));

        return expectedEmailMap;
    }
}

