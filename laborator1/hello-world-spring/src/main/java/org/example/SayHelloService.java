package org.example;

import org.springframework.stereotype.Component;

@Component
public class SayHelloService {

    public void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
