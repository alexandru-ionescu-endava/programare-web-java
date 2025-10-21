package org.example;

public class SMSNotification
        implements NotificationManager {

    @Override
    public void sendNotification() {
        System.out.println("Sending SMS Notification");
    }
}
