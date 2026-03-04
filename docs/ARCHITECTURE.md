# Arquitectura de OpsBackend

Monolito en capas con dominio desacoplado de frameworks.

## Diagrama de capas
```text
┌─────────────────────────────────────────────┐
│                   UI LAYER                  │
│         CLI (Semana 1) → REST API           │
│   Punto de entrada: recibe input del mundo  │
└────────────────────┬────────────────────────┘
                     │ llama a
┌────────────────────▼────────────────────────┐
│             APPLICATION LAYER               │
│   Casos de uso: CreateOrder, PayOrder...    │
│   Orquesta el flujo, no contiene reglas     │
└────────────────────┬────────────────────────┘
                     │ usa
┌────────────────────▼────────────────────────┐
│               DOMAIN LAYER                  │
│   Modelos: Customer, Product, Order...      │
│   Reglas de negocio puras, sin frameworks   │
│   Corazón del sistema, estable en el tiempo │
└────────────────────┬────────────────────────┘
                     │ implementado por
┌────────────────────▼────────────────────────┐
│           INFRASTRUCTURE LAYER              │
│   Repositorios (memoria → JDBC → JPA)       │
│   Base de datos, frameworks, mundo exterior │
└─────────────────────────────────────────────┘
```

## Modelo conceptual — Diagrama ER
```text
+------------------+          +------------------+
|    CUSTOMER      |          |     PRODUCT      |
+------------------+          +------------------+
| PK id            |          | PK id            |
|    name          |          |    name          |
|    email         |          |    description   |
|    active        |          |    price         |
+------------------+          |    stock         |
        |                     |    active        |
        | 1                   +------------------+
        |                             |
        | N                           | N
+------------------+          +------------------+
|      ORDER       |          |   ORDER_ITEM     |
+------------------+          +------------------+
| PK id            | 1 ————— N| PK id            |
| FK customer_id   |          | FK order_id      |
|    status        |          | FK product_id    |
|    total         |          |    quantity      |
|    created_at    |          |    unit_price    |
+------------------+          |    subtotal      |
        |                     +------------------+
        | 1
        |
        | N
+------------------+
|   TRANSACTION    |
+------------------+
| PK id            |
| FK order_id      |
|    amount        |
|    status        |
|    created_at    |
+------------------+
```

## Cardinalidades
- CUSTOMER 1 ——— N ORDER (un cliente tiene muchas órdenes)
- ORDER 1 ——— N ORDER_ITEM (una orden tiene muchos items)
- PRODUCT 1 ——— N ORDER_ITEM (un producto aparece en muchos items)
- ORDER 1 ——— N TRANSACTION (una orden puede tener varias transacciones)

## Decisiones de diseño
- `unit_price` en ORDER_ITEM guarda el precio al momento de la compra.
  Si el precio del producto cambia mañana, el historial de ventas no se altera.
- `total` en ORDER se calcula sumando los subtotales de sus items.
  Es una regla de negocio, no se ingresa manualmente.
- `status` en ORDER y TRANSACTION usan valores fijos (enums) para evitar
  datos inconsistentes en la base de datos.

## Evolución planificada
Semana 1 → Java puro, repositorios en memoria
Semana 2 → PostgreSQL + JDBC, este modelo se convierte en tablas SQL
Semana 3 → JPA, las tablas se mapean como entidades
Semana 4 → Docker Compose, seguridad JWT
Semana 5 → Frontend Angular consume la API REST