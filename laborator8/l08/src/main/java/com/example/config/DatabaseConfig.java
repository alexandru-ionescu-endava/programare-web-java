//package com.example.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import com.zaxxer.hikari.util.DriverDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
////
//
//    /// /    @Bean
//    /// /    public DataSource dataSource() {
//    /// /        return new DriverManagerDataSource(
//    /// /                "jdbc:postgresql://localhost:5432/postgres?current_schema=test_schema",
//    /// /                "alexionescu",
//    /// /                ""
//    /// /        );
//    /// /    }
////
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?current_schema=test_schema");
//        dataSource.setUsername("alexionescu");
//        dataSource.setPassword("");
//
//        dataSource.setMinimumIdle(5);
//        dataSource.setMaximumPoolSize(10);
//
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }
//}
