# Taller Sesión 8 — Proyecto Esqueleto

Proyecto base para el taller guiado de refactorización de aplicaciones con **Hibernate/JPA + Angular**.

> ⚠ Este proyecto contiene errores **a propósito**. Tu trabajo en el taller es corregirlos paso a paso siguiendo el documento `Taller_Guiado_Sesion_8.docx`.

---

## Estructura del proyecto

```
taller-sesion-8/
├── backend-pedidos/          ← Spring Boot + Hibernate (Java 17)
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/curso/pedidos/
│       │   ├── PedidosApplication.java
│       │   ├── controller/PedidoController.java     ← tiene problemas
│       │   ├── entity/
│       │   │   ├── Cliente.java
│       │   │   ├── LineaPedido.java
│       │   │   └── Pedido.java                       ← tiene problemas
│       │   └── repository/PedidoRepository.java     ← tiene problemas
│       └── resources/
│           ├── application.properties
│           └── data.sql
│
├── frontend-pedidos/         ← Angular 17 (standalone)
│   ├── package.json
│   ├── angular.json
│   └── src/
│       ├── main.ts
│       ├── styles.css
│       ├── environments/environment.ts
│       └── app/
│           ├── app.component.ts
│           ├── app.config.ts
│           └── components/pedidos/
│               └── pedidos.component.ts             ← tiene problemas
│
└── README.md
```

---

## Pre-requisitos

| Herramienta | Versión mínima | Verificar |
|---|---|---|
| Java JDK | 17 | `java --version` |
| Maven | 3.6+ | `mvn --version` |
| Node.js | 18+ | `node --version` |
| Angular CLI | 17+ | `ng version` |
| Git | cualquiera | `git --version` |
| VS Code | última | `code --version` |

Si no tienes Angular CLI instalado:
```bash
npm install -g @angular/cli
```

---

## Cómo arrancar el proyecto

### Paso 1 — Clonar e inicializar Git

```bash
# Si descargaste el ZIP, extráelo primero
cd taller-sesion-8

# Inicializa el repositorio para hacer commits por cada paso
git init
git checkout -b main
git add .
git commit -m "chore: estado inicial del taller"
```

### Paso 2 — Arrancar el backend

Abre **una terminal** en VS Code:

```bash
cd backend-pedidos
mvn spring-boot:run
```

Espera el mensaje:
```
Started PedidosApplication in X seconds
```

Verifica que funciona en otra terminal:

```bash
curl http://localhost:8080/api/pedidos
```

Deberías ver una lista de 10 pedidos en formato JSON.

> 💡 **Tip:** la consola web de H2 está disponible en
> http://localhost:8080/h2-console
> Usa `jdbc:h2:mem:pedidosdb` como JDBC URL, usuario `sa`, sin password.

### Paso 3 — Arrancar el frontend

Abre **otra terminal** en VS Code (Ctrl+Shift+ñ):

```bash
cd frontend-pedidos
npm install
npm start
```

Espera a que termine la compilación y abre en el navegador:

**http://localhost:4200**

Deberías ver una lista de pedidos.

---

## ¿Algo no funciona?

### El backend no arranca

- Verifica que estás usando Java 17+: `java --version`
- Si el puerto 8080 está ocupado, cambia `server.port=8080` en `application.properties`

### El frontend no arranca

- Si `npm install` falla, borra `node_modules/` y `package-lock.json`, y vuelve a ejecutar
- Si el puerto 4200 está ocupado: `ng serve --port 4201`

### CORS error en el navegador

Esto es **esperado** al inicio del taller. Lo arreglas en el **Paso 4** del taller guiado.
Mientras tanto, puedes hacer pruebas con `curl` directamente al backend.

### Las consultas SQL no aparecen en la consola

Verifica que `application.properties` tenga:
```properties
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```

---

## Comandos útiles durante el taller

### Backend

```bash
# Compilar
mvn clean compile

# Correr tests
mvn test

# Empaquetar
mvn clean package

# Correr con perfil específico
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Frontend

```bash
# Servir en modo desarrollo
ng serve

# Build producción
ng build --configuration production

# Generar componentes/servicios/interfaces
ng generate component nombre
ng generate service services/nombre
ng generate interface models/nombre
ng generate interceptor interceptors/nombre

# Ver el árbol de bundles
ng build --stats-json
```

### Git (cada paso del taller)

```bash
git add .
git commit -m "feat: descripción del cambio"
git log --oneline           # ver el historial
```

---

## Probar la API con curl

```bash
# Listar todos los pedidos
curl http://localhost:8080/api/pedidos

# Pedidos de un cliente (esto dispara N+1 al inicio del taller)
curl http://localhost:8080/api/pedidos/cliente/1

# Eliminar un pedido
curl -X DELETE http://localhost:8080/api/pedidos/1

# Crear un pedido (al inicio del taller esto envía la entidad cruda)
curl -X POST http://localhost:8080/api/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "numero": "PED-NEW",
    "total": 100000,
    "fechaCreacion": "2026-04-30T10:00:00"
  }'
```

---

## Datos de prueba

Al arrancar, la base se carga con:
- 5 clientes
- 10 pedidos (varios por cliente para hacer evidente el N+1)
- 15 líneas de pedido

Si quieres modificar los datos, edita `backend-pedidos/src/main/resources/data.sql` y reinicia el backend.

---

## Cómo abordar el taller

1. Lee primero el documento `Taller_Guiado_Sesion_8.docx` completo (15 minutos).
2. Realiza los 8 pasos en orden, sin saltarte ninguno.
3. Haz un commit al final de cada paso (es parte de la rúbrica de evaluación).
4. Si algo se rompe, vuelve al commit anterior con `git reset --hard HEAD~1`.
5. Al terminar, sube tu repositorio a GitHub/GitLab y entrégalo en la plataforma del curso.

---

## Soporte

Si tienes dudas durante el taller:
1. Revisa los comentarios en cada archivo (te indican qué paso lo modifica).
2. Consulta el documento del taller — cada paso tiene una sección "¿Cómo sé que funcionó?".
3. Usa los canales del curso (foro, chat, tutoría) — pero intenta avanzar primero.

¡Éxitos!
