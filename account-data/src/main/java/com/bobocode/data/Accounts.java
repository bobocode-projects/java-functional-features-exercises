package com.bobocode.data;

import com.bobocode.model.Account;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public interface Accounts {
    static Account getAccount(){
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        Random random = new Random();


        Account fakeAccount = new Account();
        fakeAccount.setFirstName(person.getFirstName());
        fakeAccount.setLastName(person.getLastName());
        fakeAccount.setEmail(person.getEmail());
        fakeAccount.setBirthday(LocalDate.of(
                person.getDateOfBirth().getYear(),
                person.getDateOfBirth().getMonthOfYear(),
                person.getDateOfBirth().getDayOfMonth()));
        fakeAccount.setBalance(BigDecimal.valueOf(random.nextInt(200_000)));
        fakeAccount.setCreationDate(LocalDateTime.now());

        return fakeAccount;
    }

    static List<Account> getAccountList(int size){
        return range(0, size)
                .mapToObj(i -> getAccount())
                .collect(toList());
    }
}
