package com.jdbctemplate.example.springWithJdbctemplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Configuration
@PropertySource("classpath:queries.properties")
@Getter // Lombok automatically generates getters
public class SqlQueryConfig {

    @Value("${sql.insert.candidate}")
    private String insert;

    @Value("${sql.update.candidate}")
    private String update;

    @Value("${sql.delete.candidate}")
    private String deleteById;

    @Value("${sql.select.candidate.by.id}")
    private String getById;

    @Value("${sql.select.all.candidate}")
    private String getAll;

   
}
