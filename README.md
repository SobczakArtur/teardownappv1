# TeardownApp v1

**TeardownApp** is a backend application that allows management of smartphone components such as manufacturers, technologies, and teardown complexity. The project was developed to strengthen skills in building RESTful APIs using Java and the Spring ecosystem.

---

## Technologies & Tools

- **Programming language:** Java 8
- **Framework:** Spring Boot, Spring Framework
- **Database:** MySQL
- **ORM:** Hibernate (JPA)
- **Testing:** JUnit 5, Mockito, MockMvc
- **Dependency Management:** Maven
- **Containerization:** Docker, Docker Compose
- **Error Handling:** Centralized Exception Handler
- **IDE:** IntelliJ IDEA
- **Database GUI:** MySQL Workbench
- **CLI Tools:** PowerShell
- **Others:** Lombok, Git, Postman

---

## Features

- Full CRUD functionality for smartphone teardown components
- One-to-Many and Many-to-One relationships between entities
- Centralized exception handling with custom error responses
- Unit tests
- Dockerized backend and database using Docker Compose

---

## Getting Started

### Prerequisites

Make sure you have installed:

- Java 8
- Maven
- Docker + Docker Compose
- MySQL Workbench *(optional, for database GUI access)*
- PowerShell *(recommended for running Docker commands on Windows)*

### Clone the repository

git clone https://github.com/SobczakArtur/teardownappv1.git  
cd teardownappv1  

### Build the project

mvn clean install

### Run with Docker Compose

docker-compose up --build  
The application will be available at: http://localhost:8080  

MySQL will be running on: localhost:3306  
Use MySQL Workbench to connect with the following credentials:

- Host: localhost
- Port: 3306
- User: appuser
- Password: apphaslo123  
<br><br>

![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(1).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(2).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(3).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(4).JPG?raw=true)
