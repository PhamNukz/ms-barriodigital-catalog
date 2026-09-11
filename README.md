# ms-barriodigital-catalog

Microservicio de dominio: gestiona el **catálogo de tipos de trámite** (nombre,
requisitos, cupo diario). Solo el rol Admin puede crear/editar tipos; el resto los
consulta para elegir uno al ingresar un trámite. Valida el JWT de Azure AD.

## Cómo correr local

Requiere Java 17, Maven y una Oracle accesible (o correr todo junto con
`barriodigital-infra`, que ya trae Oracle en Docker).

```bash
./mvnw spring-boot:run
```

Levanta en `http://localhost:8082`.

Tests: `./mvnw verify` — corren contra H2 en memoria con JWT mockeado, no
necesitan Oracle ni Azure AD real.

## Variables de entorno

| Variable | Default | Descripción |
|---|---|---|
| `DB_HOST` / `DB_PORT` / `DB_SERVICE` | `localhost` / `1521` / `XEPDB1` | Conexión a Oracle |
| `DB_USER` | `barriodigital_catalog` | Usuario Oracle dedicado a este servicio |
| `DB_PASSWORD` | — | Password de ese usuario |
| `AAD_ISSUER_URI` | — | `https://login.microsoftonline.com/<TENANT_ID>/v2.0` |
| `AAD_API_CLIENT_ID` | — | Client ID del App Registration `barriodigital-api` |
| `AAD_REQUIRED_SCOPE` | `access_as_user` | Scope exigido en el token |

Las migraciones de esquema están en `src/main/resources/db/migration` (Flyway).

## Docker

```bash
docker build -t ms-barriodigital-catalog .
docker run -p 8082:8082 --env-file .env ms-barriodigital-catalog
```

Imagen publicada automáticamente en cada push a `main`:
`ghcr.io/phamnukz/ms-barriodigital-catalog:latest`.
