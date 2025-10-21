package org.example;

public class Main {
    public static void main(String[] args) {

        SMSNotification smsNotification =
                new SMSNotification();

        EmailNotification emailNotification
                = new EmailNotification();

//        Dependency Injection - design pattern
//        injectam depentine in alte obiect din exterior
        NotificationService notificationService =
                new NotificationService(smsNotification);

        notificationService.sendNotification();
    }
}