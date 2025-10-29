package org.example;

import org.example.config.AppConfig;
import org.example.model.BankAccount;
import org.example.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        var service = context.getBean(BankService.class);
        BankAccount bankAccount = new BankAccount(
                "Alina",
                100d
        );
        BankAccount bankAccount2 = new BankAccount(
                "Cosmin",
                50d
        );

//        service.transfer(bankAccount, bankAccount2, 10d);

        service.transfer(bankAccount, bankAccount2, -3d);
        service.getNumber();
    }
}