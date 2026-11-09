# TeardownApp v1

REST API for managing **substrate** records and their **assembly blocks**, built with Java 17, Spring Boot,
Spring Data JPA and MySQL. Fully containerized: the application and the database start with one command.

> **In short:** a layered Spring Boot backend with a one-to-many data model, input validation, global exception
> handling, partial updates (`PATCH`) that also handle nested records, automated tests and Docker Compose.
> A second branch adds JWT authentication and role-based access control.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Docker-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## What does it do?

A **substrate** is described by its technology (e.g. `FOUR_L_C`, which means "4 L C" with core material R4F4),
complexity and area. The API stores substrates together with their **assembly blocks** (functional blocks such as
`APP`, `BAS`, `RF` or `POWER`) and **calculates the substrate cost and test cost on the server** from technology,
complexity and area.

**Required fields:** `assemblyName`, `technology`, `complexity`.
**Optional fields:** `substrateMarking`, `manufacturer`, `areaValue`, `thickness`, `weight`, `assemblyBlocks`.

**Calculated by the server** (you do not send them):
- `technologyDescription`, `technologyCoreMaterial`, `technologyMetalLs`: taken from the chosen `technology`
- `substrateCost`, `testCost`: calculated from technology, complexity and area (only when `areaValue` is provided)
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

---

## What to look at (for a quick code review)

| Topic | Where / what |
|---|---|
| **Layered architecture** | Controller → Service (interface + implementation) → Repository, constructor injection |
| **Data model** | One-to-many relation: `Substrate` ↔ `AssemblyBlock`, parent reference kept in sync in the service layer |
| **PATCH with nested data** | `SubstrateServiceImpl#partiallyUpdatedSubstrate`: updates only the fields that were sent, matches existing assembly blocks by ID and adds new ones |
| **Calculated fields** | `SubstrateCost` utility plus JPA lifecycle callbacks (`@PrePersist` / `@PreUpdate`) calculate technology details and costs before every save |
| **Validation** | Bean Validation (`@Valid`, `@NotBlank`, `@NotNull`, `@Positive`) on request bodies |
| **Error handling** | Custom `ResourceNotFoundException` and global exception handling |
| **Tests** | Unit tests (JUnit 5, Mockito) and integration tests (MockMvc + H2) |
| **Infrastructure** | `Dockerfile` and `docker-compose.yml` (application + MySQL) |
| **Authentication** | Separate branch [`Authentication_Authorization`](https://github.com/SobczakArtur/teardownappv1/tree/Authentication_Authorization): Spring Security, JWT, `ROLE_USER` / `ROLE_ADMIN` |

---

## Project versions

| Version | Branch | Description |
|---|---|---|
| **Standard CRUD** (this README) | `master` | All endpoints are public. Simplest way to run and test the API. |
| **Authentication & Authorization** | [`Authentication_Authorization`](https://github.com/SobczakArtur/teardownappv1/tree/Authentication_Authorization) | Registration, login, JWT and role-based access control. |

---

## Tech stack

| Area | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot, Spring Web, Spring Data JPA, Bean Validation |
| Database | MySQL (runtime), H2 (tests) |
| Build tool | Maven (wrapper included) |
| Testing | JUnit 5, Mockito, MockMvc |
| Containers | Docker, Docker Compose |

---

## Getting started

### Option A: Docker (recommended)

Requires Docker and Docker Compose.

```bash
git clone https://github.com/SobczakArtur/teardownappv1.git
cd teardownappv1
docker-compose up --build
```

The API is available at `http://localhost:8080`. Stop everything with `docker-compose down`.

### Option B: local run with Maven

Requires JDK 17 and a running MySQL instance whose settings match the datasource configuration in
`src/main/resources`.

```bash
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

Note: `PUT` and `PATCH` validate the request body like `POST`, so `assemblyName`, `technology` and `complexity` must be included in every request.

---

## Testing

```bash
./mvnw test
```

Unit tests use JUnit 5 and Mockito; integration tests use MockMvc with an in-memory H2 database.

---

## Possible next steps

Ideas I would pick up next: a DTO layer with mappers (including a dedicated `PATCH` request without mandatory fields),
`@Transactional` on service methods, pagination, and a CI workflow in GitHub Actions.

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
