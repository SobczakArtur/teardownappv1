# TeardownApp v1

REST API for managing **substrate** records and their **assembly blocks**, built with Java 17, Spring Boot 3,
Spring Data JPA and MySQL. Fully containerized: the application and the database run with Docker Compose.

> **In short:** a layered Spring Boot backend with a one-to-many data model, server-side cost calculation,
> global exception handling with JSON error responses, partial updates (`PATCH`) that also handle nested records,
> unit and controller tests, and Docker Compose. A second branch adds user registration, login and JWT authentication.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Docker-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## What does it do?

A **substrate** is described by its technology (e.g. `FOUR_L_C`, which means "4 L C" with core material R4F4),
complexity and area. The API stores substrates together with their **assembly blocks** (functional blocks such as
`APP`, `BAS`, `RF` or `POWER`) and **calculates the substrate cost and test cost on the server** from technology,
complexity and area.

**Fields:** `assemblyName`, `substrateMarking`, `manufacturer`, `technology`, `complexity`, `areaValue`,
`thickness`, `weight`, `assemblyBlocks`. On this branch no field is mandatory.

**Calculated by the server** (you do not send them):
- `technologyDescription`, `technologyCoreMaterial`, `technologyMetalLs`: taken from the chosen `technology`
- `substrateCost`, `testCost`: calculated when `technology`, `complexity` and `areaValue` are all provided
- `assemblyName` of every assembly block: copied from the parent substrate

**Allowed enum values**
- `technology`: `TWO_L_C`, `TWO_L_F`, `TWO_L_FV`, `THREE_L_C`, `THREE_L_F`, `THREE_L_FV`, `FOUR_L_C`, `FOUR_L_B`, `FOUR_L_F`, `FOUR_L_FV`
- `complexity`: `SIMPLE`, `LOW`, `MEDIUM`, `HIGH`
- `functionalBlock`: `APP_BAS`, `APP`, `BAS`, `CONN`, `RF`, `POWER`

### Example: `POST /api/substrate`

Request body:

```json
{
  "assemblyName": "Sample board A",
  "substrateMarking": "ABC-123",
  "manufacturer": "Example Manufacturer",
  "technology": "FOUR_L_C",
  "complexity": "MEDIUM",
  "areaValue": { "areaValue": 12.5 },
  "thickness": 0.8,
  "weight": 1.2,
  "assemblyBlocks": [
    { "functionalBlock": "APP_BAS" },
    { "functionalBlock": "POWER" }
  ]
}
```

Response `201 Created` (cost values rounded to two decimals):

```json
{
  "substrateId": 1,
  "assemblyName": "Sample board A",
  "substrateMarking": "ABC-123",
  "manufacturer": "Example Manufacturer",
  "technology": "FOUR_L_C",
  "technologyDescription": "4 L C",
  "technologyCoreMaterial": "R4F4",
  "areaValue": { "areaValue": 12.5 },
  "technologyMetalLs": 4,
  "complexity": "MEDIUM",
  "thickness": 0.8,
  "weight": 1.2,
  "substrateCost": 3.06,
  "testCost": 2.25,
  "assemblyBlocks": [
    { "assemblyBlocksId": 1, "assemblyName": "Sample board A", "functionalBlock": "APP_BAS" },
    { "assemblyBlocksId": 2, "assemblyName": "Sample board A", "functionalBlock": "POWER" }
  ]
}
```

Try it with curl:

```bash
curl -X POST http://localhost:8080/api/substrate \
  -H "Content-Type: application/json" \
  -d '{"assemblyName":"Sample board A","technology":"FOUR_L_C","complexity":"MEDIUM","areaValue":{"areaValue":12.5}}'
```

### Error response example

`GET /api/substrate/999` for a substrate that does not exist returns `404`:

```json
{
  "timestamp": "2026-01-15T10:15:30.123",
  "status": 404,
  "error": "Not Found",
  "message": "Substrate not found with ID: 999"
}
```

---

## What to look at (for a quick code review)

| Topic | Where / what |
|---|---|
| **Layered architecture** | Controller → Service (interface + implementation) → Repository, constructor injection, `@Qualifier` for explicit bean selection |
| **Data model** | One-to-many relation: `Substrate` ↔ `AssemblyBlocks`, parent reference kept in sync in the service layer |
| **PATCH with nested data** | `SubstrateServiceImpl#partiallyUpdatedSubstrate`: updates only the fields that were sent, matches existing assembly blocks by ID and adds new ones |
| **Calculated fields** | `SubstrateCost` utility plus JPA lifecycle callbacks (`@PrePersist` / `@PreUpdate`) calculate technology details and costs before every save |
| **Error handling** | Custom exceptions and a global `@RestControllerAdvice` returning JSON (`timestamp`, `status`, `error`, `message`) |
| **Tests** | Unit tests for the service layer and the cost calculation (JUnit 5, Mockito) and controller tests (`@WebMvcTest`, MockMvc, mocked service) |
| **Infrastructure** | `Dockerfile` and `docker-compose.yml` (application + MySQL), configuration through environment variables |
| **Authentication** | Separate branch [`Authentication_Authorization`](https://github.com/SobczakArtur/teardownappv1/tree/Authentication_Authorization): Spring Security, stateless JWT authentication, BCrypt-hashed passwords (see the section below) |

---

## Project versions

| Version | Branch | Description |
|---|---|---|
| **Standard CRUD** (this README) | `master` | All endpoints are public. Simplest way to run and test the API. |
| **Authentication & Authorization** | [`Authentication_Authorization`](https://github.com/SobczakArtur/teardownappv1/tree/Authentication_Authorization) | Registration, login and JWT authentication; all substrate endpoints require a token. |

---

## Tech stack

| Area | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.2, Spring Web, Spring Data JPA, Hibernate |
| Database | MySQL 8 |
| Build tool | Maven (wrapper included) |
| Libraries | Lombok |
| Testing | JUnit 5, Mockito, MockMvc (`@WebMvcTest`) |
| Containers | Docker, Docker Compose |

---

## Getting started

### Option A: Docker (recommended)

Requires JDK 17 (to build the JAR), Docker and Docker Compose.

```bash
git clone https://github.com/SobczakArtur/teardownappv1.git
cd teardownappv1
./mvnw clean package -DskipTests
docker-compose up --build
```

The `Dockerfile` copies the JAR from `target/`, so the project has to be built first (on Windows use `mvnw.cmd`).
The API is available at `http://localhost:8080`. MySQL is exposed on host port `3307`.
If the application container stops because MySQL has not finished starting yet, run `docker-compose up` again.
Stop everything with `docker-compose down`.

### Option B: run the application locally

The application reads its datasource settings from environment variables (there are no defaults), so set them first.
You can start only the database from Docker Compose:

```bash
docker-compose up -d mysql-db
```

Then, in the same terminal (Linux/macOS syntax):

```bash
export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3307/teardownlibrary?allowPublicKeyRetrieval=true&useSSL=false"
export SPRING_DATASOURCE_USERNAME=appuser
export SPRING_DATASOURCE_PASSWORD=<password from docker-compose.yml>
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
export SPRING_JPA_SHOW_SQL=true
export SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.MySQL8Dialect

./mvnw spring-boot:run
```

---

## API endpoints

Base path: `/api/substrate`

| Method | Endpoint | Description | Success | Not found |
|---|---|---|---|---|
| `GET` | `/api/substrate` | List all substrates | 200 | n/a |
| `GET` | `/api/substrate/{id}` | Get a substrate by ID | 200 | 404 |
| `POST` | `/api/substrate` | Create a substrate (with assembly blocks) | 201 | n/a |
| `PUT` | `/api/substrate/{id}` | Replace a substrate | 200 | 404 |
| `PATCH` | `/api/substrate/{id}` | Partially update a substrate | 200 | 404 |
| `DELETE` | `/api/substrate/{id}` | Delete a substrate | 200 | 404 |

All endpoints on this branch are public. Try them with Postman, curl or any HTTP client.

---

## Branch `Authentication_Authorization`: JWT authentication

The [`Authentication_Authorization`](https://github.com/SobczakArtur/teardownappv1/tree/Authentication_Authorization)
branch contains the same API secured with **Spring Security and JWT**.

**What is different from `master`**
- User registration and login (`POST /api/auth/register`, `POST /api/auth/login`). Passwords are stored hashed with BCrypt.
- Stateless security: login returns a signed JWT (HS256, valid for 10 hours) and a request filter validates it on every call.
  Everything except `/api/auth/**` requires a token.
- Bean Validation is active (`jakarta.validation`): `assemblyName`, `technology` and `complexity` are mandatory,
  also for `PUT` and `PATCH`.
- Spring Boot 3.2.5. The JWT secret comes from `jwt.secret` / `JWT_SECRET`, and the datasource settings have local defaults,
  so the application starts without extra configuration when MySQL runs on port `3307`.
- Tests: service and cost-calculation unit tests, a JWT service test, and controller integration tests (`@SpringBootTest`,
  MockMvc, in-memory H2 database in MySQL mode, `test` profile) in which the security filter chain is replaced by a permit-all test configuration.

**Usage**

```bash
# 1. Register
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"demo123"}'

# 2. Log in (the response is {"token":"<jwt>"})
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"demo123"}'

# 3. Call the API with the token
curl http://localhost:8080/api/substrate \
  -H "Authorization: Bearer <jwt>"
```

Every registered user gets `ROLE_USER`. Role-restricted endpoints are not implemented yet, and outside local development
you should set your own `JWT_SECRET`.

---

## Testing

```bash
./mvnw test
```

- **Service tests** (`SubstrateServiceImplTest`): JUnit 5 and Mockito with a mocked repository.
- **Cost calculation tests** (`SubstrateCostTest`): expected values for valid input, plus null, zero and negative parameters.
- **Controller tests** (`SubstrateControllerTest`): `@WebMvcTest` with MockMvc and a mocked service, covering success and not-found cases for every endpoint.

---

## Possible next steps

Ideas I would pick up next: a DTO layer with mappers, `@Transactional` on service methods, integration tests against a real database, pagination, and a CI workflow in GitHub Actions.

---

## Screenshots

| | |
|---|---|
| ![CRUD example 1](images/teardown_app%20%281%29.JPG) | ![CRUD example 2](images/teardown_app%20%282%29.JPG) |
| ![CRUD example 3](images/teardown_app%20%283%29.JPG) | ![CRUD example 4](images/teardown_app%20%284%29.JPG) |

---

## Author

**Artur Sobczak**: [GitHub](https://github.com/SobczakArtur) · [LinkedIn](https://www.linkedin.com/in/artur-sobczak-03724a175/)

## License

Released under the MIT License. See [LICENSE](LICENSE) for details.
