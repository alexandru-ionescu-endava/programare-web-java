package org.example;

import org.example.config.AppConfig;
import org.example.service.BankService;
import org.example.service.NotificationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        var bankService =
                context.getBean(BankService.class);
        var bankService1 =
                context.getBean(BankService.class);

        var emailNotification =
                context.getBean(NotificationService.class);

        var smsNotification =
                context.getBean(NotificationService.class);

        var pushNotification =
                context.getBean(NotificationService.class);

        System.out.println(bankService1.hashCode());
        System.out.println(bankService.hashCode());
        System.out.println(emailNotification.hashCode());
        System.out.println(smsNotification.hashCode());
        System.out.println(pushNotification.hashCode());
    }
}