package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        DatabaseService databaseService =
                context.getBean(DatabaseService.class);

        databaseService.connect();
    }
}