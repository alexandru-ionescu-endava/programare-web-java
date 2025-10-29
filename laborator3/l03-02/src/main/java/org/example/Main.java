package org.example;

import org.example.config.AppConfig;
import org.example.model.BankAccount;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context =
                new AnnotationConfigApplicationContext(AppConfig.class);

//        var bankService =
//                context.getBean(BankAccount.class);
//


    }
}