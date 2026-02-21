# DEVLOG - OpsBackend

## 🟩 Semana 1 — Día 0
📌 Tema: Setup inicial del proyecto  
🎯 Objetivo: dejar base técnica y documental lista para empezar dominio.

### Qué hicimos
- Creamos proyecto Gradle con Java.
- Verificamos estructura de carpetas base por capas.
- Definimos `README.md` inicial.
- Definimos `docs/ARCHITECTURE.md` inicial.

### Qué aprendí
- La arquitectura en capas evita acoplamiento temprano.
- El dominio debe mantenerse libre de frameworks.
- Documentar desde el día 0 reduce errores de dirección.

### Decisiones técnicas y por qué
- Monolito en capas primero: menor complejidad para aprender.
- CLI antes de REST: foco en lógica sin distracción HTTP.
- Evolución incremental: InMemory -> JDBC -> JPA.
