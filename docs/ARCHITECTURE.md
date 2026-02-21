# Arquitectura de OpsBackend

Monolito en capas con dominio desacoplado de frameworks.

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
