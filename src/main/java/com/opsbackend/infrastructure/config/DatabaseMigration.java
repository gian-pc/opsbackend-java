package com.opsbackend.infrastructure.config;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {

    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/opsbackend",
                        "opsuser",
                        "opspass"
                )
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
        System.out.println("Migración ejecutada correctamente");
    }
}