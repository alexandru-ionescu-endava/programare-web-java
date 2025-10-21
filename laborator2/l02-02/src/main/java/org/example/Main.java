package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

//        IoC (Inversion of Control)
//        Design principle
//        shiftam responsabilitatea de create
//        si gestiune a obiectelor
//        de la programator la framework

//        Spring framework - open source platform
//        include module reutilizabile eg: spring-context

//        Application Context - IoC container in care inregistram bean uri

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        BankAccount bankAccount4 =
                context.getBean( BankAccount.class);
        System.out.println(bankAccount4.getAccountOwner());

//        get from context by type BankAccount.class
        BankAccount bankAccount =
                context.getBean(BankAccount.class);

        System.out.println(bankAccount.getAccountNumber());

//        get from context by name BankAccount.class
//        BankAccount bankAccount2 =
//                context.getBean("my-bank-account", BankAccount.class);
//        System.out.println(bankAccount2.getAccountOwner());
//
//        BankAccount bankAccount3 =
//                context.getBean("my-bank-account-3", BankAccount.class);
//        System.out.println(bankAccount3.getAccountOwner());


    }


}