package org.example.service;

import org.example.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankService {

    public void transfer(BankAccount from,
                         BankAccount to,
                         Double amount) {

        if (amount < -1)
            throw new RuntimeException("invalid amount");

        System.out.println(from.getAccountOwner()
                + " transferred to " +
                to.getAccountOwner() + " " + amount);
    }

    public Integer getNumber() {
        return 99;
    }
}
