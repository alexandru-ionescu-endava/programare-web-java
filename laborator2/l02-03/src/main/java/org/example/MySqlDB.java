package org.example;

import org.springframework.stereotype.Component;

@Component("mysql")
public class MySqlDB
        implements Database {

    @Override
    public void connectToDB() {
        System.out.println("Connected to MySQL");
    }
}
