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
## Día 3 — Modelos Order y OrderItem
**Fecha:** 2026-03-04
**Rama:** feat/day-01-domain-customer

### Qué hicimos
- Creamos `OrderItem` con atributos inmutables y cálculo automático de subtotal
- Creamos enum `OrderStatus` con valores: PENDING, CONFIRMED, CANCELLED, DELIVERED
- Creamos `Order` con lista de items, cálculo automático de total y transiciones de status
- Implementamos TDD en todas las clases — primero rojo, luego verde

### Lo que aprendí
- `List<OrderItem>` — cómo guardar una colección de objetos en Java
- `ArrayList` — implementación de List que crece automáticamente
- Enum — lista fija de valores permitidos, más seguro que String para estados
- `LocalDateTime` — tipo de dato para fechas y horas en Java moderno
- `assertNotNull` — verifica que un valor no sea null sin importar su valor exacto
- Streams — forma moderna de procesar listas: `.stream().mapToDouble().sum()`
- `IllegalStateException` vs `IllegalArgumentException` — errores de estado vs errores de datos

### Decisiones técnicas
- **Todos los atributos de OrderItem son `final`** — un item registrado no se modifica. Si hay error se cancela la orden y se crea una nueva.
- **`total` se recalcula en `addItem`** — nunca se ingresa a mano. Es una regla de negocio que el sistema controla.
- **Transiciones de status tienen reglas** — DELIVERED y CANCELLED son estados finales. No puedes confirmar una orden cancelada ni cancelar una entregada.
- **Enum para OrderStatus** — evita valores inválidos como "pending", "PENDIENTE" o typos.

### Commits del día
- `feat: modelo OrderItem con estructura base y cálculo de subtotal`
- `feat: validaciones de id, productId, quantity y unitPrice en OrderItem`
- `feat: modelo Order con estructura base, OrderStatus enum y primer test`
- `feat: validaciones de id y customerId en Order`
- `feat: comportamiento addItem y cálculo automático de total en Order`
- `feat: cambio de status con reglas de transición en Order`

## Día 4 — Modelo Transaction
**Fecha:** 2026-03-07
**Rama:** feat/day-04-domain-transaction

### Qué hicimos
- Creamos enum `TransactionStatus` con valores: PENDING, APPROVED, REJECTED
- Creamos clase `Transaction` completamente inmutable
- Validaciones: id, orderId obligatorios, amount mayor a cero, status no null
- Tests con TDD cubriendo todos los casos

### Lo que aprendí
- Inmutabilidad: objeto que no cambia después de crearse, se logra con private final y sin setters
- NullPointerException vs IllegalArgumentException — NPE es referencia null, IAE es valor inválido
- requireNonNull para enums, requireText para Strings
- Transaction es inmutable porque una transacción registrada no se modifica — si falla se crea una nueva

### Decisiones técnicas
- **Transaction completamente inmutable** — representa un hecho registrado en el sistema. Los hechos no se modifican.
- **TransactionStatus como enum** — type safety, el compilador impide valores inválidos.
- **requireNonNull para status** — lanza NullPointerException estándar de Java, no IllegalArgumentException.

### Preguntas de entrevista
- ¿Qué es un objeto inmutable y cómo lo implementas?
- ¿Diferencia entre NullPointerException e IllegalArgumentException?
- ¿Por qué enum sobre String para estados?

### Commits del día
- feat: modelo Transaction con estructura base y primer test
- feat: validaciones de id, orderId, amount y status en Transaction