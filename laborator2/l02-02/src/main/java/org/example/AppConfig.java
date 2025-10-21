package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    public BankAccount bankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);
        bankAccount.setAccountNumber("00A1");
        bankAccount.setAccountOwner("Andrei");
        return bankAccount;
    }

    @Bean(name = "my-bank-account")
    public BankAccount bankAccount2() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(2);
        bankAccount.setAccountNumber("00B2");
        bankAccount.setAccountOwner("Alina");
        return bankAccount;
    }

    @Bean(value = "my-bank-account-3")
    public BankAccount bankAccount3() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(3);
        bankAccount.setAccountNumber("00C3");
        bankAccount.setAccountOwner("Cosmin");
        return bankAccount;
    }

    @Bean("my-bank-account-4")
    @Primary
    public BankAccount bankAccount4() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(4);
        bankAccount.setAccountNumber("00D4");
        bankAccount.setAccountOwner("Andreea");
        return bankAccount;
    }
}
