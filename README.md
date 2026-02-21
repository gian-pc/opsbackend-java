# OpsBackend

Sistema de gestión de operaciones para aprender Backend Java desde cero.

## Objetivo

Construir un monolito en capas con Customers, Products, Orders y Transactions.

## Stack actual (Semana 1)

- Java 17
- Gradle
- JUnit 5
- CLI (interfaz inicial)

## Arquitectura (monolito en capas)

- `ui`: entrada/salida (CLI ahora, REST después)
- `application`: casos de uso y orquestación
- `domain`: reglas de negocio puras
- `infrastructure`: persistencia y adaptadores externos

## Ejecutar validación básica

`./gradlew test`

## Estado actual

Día 0 en progreso: setup inicial y documentación base.
