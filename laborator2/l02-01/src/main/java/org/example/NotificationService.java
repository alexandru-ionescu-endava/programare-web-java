package org.example;

public class NotificationService {

    private NotificationManager notificationManager;

    public NotificationService(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
        System.out.println(notificationManager.getClass());
    }

    public void sendNotification() {
        notificationManager.sendNotification();
    }
}
