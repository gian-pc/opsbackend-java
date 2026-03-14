# OpsBackend

Backend Java para gestión de operaciones empresariales — clientes, productos,
órdenes y pagos. Arquitectura en capas diseñada para escalar a microservicios.

## Stack

| Tecnología | Uso |
|---|---|
| Java 17 | Lenguaje principal |
| Gradle | Build y dependencias |
| PostgreSQL + Docker | Base de datos |
| Spring Boot | API REST |
| Spring Security + JWT | Autenticación y autorización |
| JUnit5 + Mockito | Testing unitario e integración |
| Testcontainers | Tests con base de datos real |
| GitHub Actions | CI/CD |

## Arquitectura

Monolito en capas con dominio desacoplado de frameworks. Cada capa tiene una
responsabilidad clara:

- `domain` — modelos y reglas de negocio puras, sin dependencias externas
- `application` — casos de uso que orquestan el flujo
- `infrastructure` — repositorios, base de datos, frameworks
- `ui` — CLI en Semana 1, REST API desde Semana 3

La separación permite reemplazar cualquier implementación de infraestructura
sin tocar el dominio. En la Semana 2, los repositorios en memoria se reemplazan
por JDBC sin modificar una sola línea de lógica de negocio.

Ver diagrama y modelo ER en [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md)

## Progreso

- [x] Semana 1 — Dominio puro en Java
  - [x] Modelos: Customer, Product, Order, OrderItem, Transaction
  - [x] Validaciones y reglas de negocio en cada modelo
  - [x] Repositorios: interfaces en domain, implementaciones en memoria
  - [x] Casos de uso: CreateCustomer, CreateOrder
  - [x] CLI que conecta todas las capas
- [ ] Semana 2 — PostgreSQL + Docker + JDBC + Flyway
- [ ] Semana 3 — Spring Boot + REST + JPA + Swagger
- [ ] Semana 4 — Spring Security + Docker Compose + GitHub Actions
- [ ] Semana 5 — Frontend Angular

## Cómo correr
```bash
# Correr tests
./gradlew test

# Correr la CLI (Semana 1)
./gradlew run
```

## Decisiones de diseño

- **TDD estricto** — cada clase tiene su test antes de la implementación
- **Conventional Commits** — historial limpio y legible
- **Ramas por feature** — una rama por día de desarrollo
- **DEVLOG** — decisiones técnicas documentadas en [docs/DEVLOG.md](docs/DEVLOG.md)

## Autor

[gianpc](https://github.com/gian-pc) — Backend Java Developer