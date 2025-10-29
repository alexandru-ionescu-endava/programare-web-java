package org.example.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BankAccount {

    public BankAccount() {
        System.out.println("Created Bank Account");
    }
}
