# OpsBackend

Sistema de gestión de operaciones empresariales — Backend Java con arquitectura
en capas, PostgreSQL, Spring Boot y Spring Security.

## ¿Qué hace este sistema?
Gestión completa de operaciones de negocio:
- Clientes (alta, baja, modificación)
- Productos (inventario, stock)
- Órdenes (creación, estados, items)
- Transacciones y pagos (validaciones, auditoría)

## Stack tecnológico
| Tecnología | Uso |
|---|---|
| Java 17 | Lenguaje principal |
| Gradle | Build y dependencias |
| PostgreSQL + Docker | Base de datos (Semana 2) |
| Spring Boot | API REST (Semana 3) |
| Spring Security + JWT | Autenticación (Semana 4) |
| JUnit5 | Testing |
| GitHub Actions | CI/CD (Semana 4) |

## Arquitectura
Monolito en capas desacoplado de frameworks:
- **domain** — modelos y reglas de negocio puras
- **application** — casos de uso y orquestación
- **infrastructure** — repositorios e implementaciones
- **ui** — CLI (Semana 1) → REST API (Semana 3)

Ver diagrama completo en [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md)

## Estado actual
- [x] Semana 1 — Dominio puro en Java
    - [x] Modelos: Customer, Product, Order, OrderItem, Transaction
    - [x] Repositorios en memoria
    - [x] Casos de uso: CreateCustomer, CreateOrder
    - [x] CLI funcional
- [ ] Semana 2 — PostgreSQL + Docker + JDBC
- [ ] Semana 3 — Spring Boot + REST + JPA
- [ ] Semana 4 — Security + Docker Compose + CI/CD
- [ ] Semana 5 — Frontend Angular

## Prácticas de desarrollo
- TDD con JUnit5
- Conventional Commits
- Ramas por feature
- Documentación técnica en /docs

## Cómo correr el proyecto
```bash
./gradlew test
```

## Autor
gianpc — Backend Java Developer