package org.example.service;

import org.example.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BankSystem {

    private final BankAccount bankAccount;

    @Autowired
    public BankSystem(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
