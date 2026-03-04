# DEVLOG — OpsBackend

## Día 1 — Modelo Customer
**Fecha:** 2026-02-21
**Rama:** feat/day-01-domain-customer

### Qué hicimos
- Creamos la clase `Customer` en `domain/model` con atributos: id, name, email, active
- Implementamos validaciones: id obligatorio, nombre mínimo 3 caracteres, formato de email
- Implementamos comportamiento: changeName, changeEmail, deactivate
- Creamos `CustomerTest` con 7 tests usando TDD — primero rojo, luego verde

### Lo que aprendí
- TDD: escribir el test antes que el código obliga a pensar en el comportamiento esperado
- AAA pattern: Arrange, Act, Assert — estructura clara para cada test
- `assertThrows` — cómo testear que un método lanza una excepción
- `private final` — garantiza que el id nunca cambie después de crearse
- Los métodos de comportamiento (`changeName`, `changeEmail`) reutilizan las mismas validaciones del constructor

### Decisiones técnicas
- **`private final String id`** — el id es inmutable por diseño. Un cliente puede cambiar de nombre o email, pero su identificador es permanente.
- **`unit_price` en ORDER_ITEM** — guardamos el precio al momento de la compra para no perder el historial si el precio cambia.
- **No hay `setActive(boolean)`** — solo existe `deactivate()`. Es una decisión de negocio deliberada: si mañana necesitas reactivar, agregas `activate()` con su propia lógica y validaciones.
- **Validaciones en métodos de comportamiento** — `changeName` llama a `validateName` igual que el constructor. La regla se cumple siempre, no solo al crear.

### Commits del día
- `feat: modelo Customer con estructura base y primer test de creación`
- `feat: validación de id obligatorio en Customer`
- `feat: validación de nombre mínimo 3 caracteres en Customer`
- `feat: validación de formato de email en Customer`
- `feat: comportamiento changeName, changeEmail y deactivate en Customer`