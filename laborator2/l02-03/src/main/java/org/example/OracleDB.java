package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("oracle")
public class OracleDB
        implements Database {

    @Override
    public void connectToDB() {
        System.out.println("Connected to Oracle");
    }
}
