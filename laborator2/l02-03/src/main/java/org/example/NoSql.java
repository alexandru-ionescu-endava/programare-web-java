package org.example;

import org.springframework.stereotype.Component;

@Component("nosql")
public class NoSql
        implements Database {

    @Override
    public void connectToDB() {
        System.out.println("Connected to Nosql");
    }

}
