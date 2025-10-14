package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
//        SayHelloService sayHelloService = new SayHelloService();
//        sayHelloService.sayHello("World");

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SayHelloService service = context.getBean(SayHelloService.class);
        service.sayHello("World!!!");
    }
}