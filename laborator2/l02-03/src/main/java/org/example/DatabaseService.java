package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class DatabaseService {

//    private final Database database;

//    constructor injection
//    recommended
//    immutable
//    @Autowired
//    public DatabaseService(@Qualifier("oracle") Database database) {
//        this.database = database;
//        System.out.println(database.getClass());
//    }

//    field injection
//    not recommended
//    @Autowired
//    @Qualifier("mysql")
//    private Database database;


//    setter injection
//     optional dependencies
    private Database database;

    @Autowired
    void setDatabase(@Qualifier("nosql") Database database) {
        this.database = database;
    }

    public void connect() {
        database.connectToDB();
    }
}
