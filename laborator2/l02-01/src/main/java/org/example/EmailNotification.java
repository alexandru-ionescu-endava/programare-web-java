package org.example;

public class EmailNotification
        implements NotificationManager {

    @Override
    public void sendNotification() {
        System.out.println("Sending Email notification");
    }
}
