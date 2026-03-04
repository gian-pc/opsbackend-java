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
## Día 2 — Modelo Product
**Fecha:** 2026-02-21
**Rama:** feat/day-01-domain-customer

### Qué hicimos
- Creamos la clase `Product` en `domain/model` con atributos: id, name, description, price, stock, active
- Implementamos validaciones: id obligatorio, name obligatorio, price mayor a cero, stock no negativo
- Implementamos comportamiento: reduceStock, addStock, deactivate
- Creamos `ProductTest` con tests usando TDD — primero rojo, luego verde

### Lo que aprendí
- `double` necesita delta en `assertEquals` por imprecisión de decimales en binario
- Un error de compilación (`cannot find symbol`) es diferente a un test fallido — el compilador no encuentra el método, no es que el test falle
- Stock puede ser cero (agotado) pero price no puede ser cero — cada regla tiene su razón de negocio
- Las reglas de negocio protegen el sistema de estados inválidos

### Decisiones técnicas
- **`validatePrice`** — price debe ser mayor a cero, no mayor o igual. Un producto sin precio no tiene sentido en un ERP.
- **`reduceStock`** valida dos cosas: que la cantidad sea mayor a cero Y que no supere el stock disponible. Dos reglas, dos mensajes de error diferentes.
- **`addStock`** solo valida que la cantidad sea mayor a cero — no tiene límite superior porque un almacén puede recibir cualquier cantidad.
- **No hay `setStock(int)`** — el stock solo se modifica a través de `reduceStock` y `addStock`. Así el sistema controla siempre cómo cambia el inventario.

### Commits del día
- `feat: modelo Product con estructura base y primer test de creación`
- `feat: validación de id obligatorio en Product`
- `feat: validación de name y price en Product`
- `feat: validación de stock no negativo en Product`
- `feat: comportamiento reduceStock, addStock y deactivate en Product`