# 🧱 TeardownApp v1

Spring Boot CRUD application with JWT authentication and Dockerized MySQL database.

---

## 🚀 About the Project

**TeardownApp v1** is a backend web application built with **Spring Boot**.  
It demonstrates how to design and implement a complete **CRUD system** with  
authentication and authorization based on **JWT (JSON Web Token)**.

This project is fully containerized using **Docker** and **Docker Compose**,  
allowing you to easily run both the Spring Boot application and a MySQL database locally.

---

## 🧩 Features

- 🔐 **JWT Authentication & Authorization**
- 🧰 **Full CRUD operations** (Create, Read, Update, Delete)
- 🧱 **MySQL Database** (Dockerized)
- 🌱 **Spring Data JPA & Hibernate**
- ⚙️ **Global Exception Handling**
- 🧪 **Unit & Integration Tests (JUnit + MockMvc + H2)**
- 🐳 **Docker Compose setup**
- 🧾 **Layered Architecture (Controller → Service → Repository)**

---

## 🧰 Technologies Used

| Layer | Technology |
|-------|-------------|
| Backend | Java 17, Spring Boot |
| Security | Spring Security, JWT |
| Database | MySQL, H2 (for tests) |
| Tools | Docker, Docker Compose |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito, MockMvc |

---

## ⚙️ Getting Started

### 1️⃣ Clone the repository

git clone https://github.com/SobczakArtur/teardownappv1.git  
cd teardownapp-v1

2️⃣ Run with Docker Compose

docker-compose up --build


The backend will be available at:  
👉 http://localhost:8080

The MySQL database will be available at:  
👉 localhost:3307

## 🔐 Authentication

The application uses **JWT (JSON Web Token)** for authentication and authorization.

### ▶️ How to log in and get a JWT token

1. **Send a POST request to the login endpoint:**
   
POST /api/v1/auth/authenticate

Example request body:

{  
  "username": "artur",  
  "password": "haslo123"  
}

2. **The response will contain a JWT token:**
   
{  
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."  
}

3. **Use this token in the Authorization header when accessing secured endpoints:**

Authorization: Bearer <your_token_here>

### 🧪 Running Tests

**Run all tests:**

mvn test


**Test types:**

Unit tests: service and controller layers (with mocks)  
Integration tests: real Spring context + H2 in-memory database

### 🧱 Project Structure

src/  
 ├── main/  
 │    ├── java/pl/sobczakartur/teardownappv1/  
 │    │     ├── auth/                # JWT + security configuration  
 │    │     ├── mainelectronics/     # CRUD features (e.g., Substrate)  
 │    │     ├── exceptions/          # Global error handling  
 │    │     └── TeardownAppV1.java   # Main Spring Boot class  
 │    └── resources/  
 │          ├── application.properties  
 │          └── data.sql (optional test data)  
 └── test/  
      └── ... (unit and integration tests)  
      
### 🧑‍💻 Author  
Artur Sobczak  
📧 e-mail: sobczak.artur88@gmail.com  
💼 LinkedIn: https://www.linkedin.com/in/artur-sobczak-03724a175/  

### 📜 License  
This project is licensed under the MIT License — feel free to use it for learning and development.
 
<br><br>

![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(1).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(2).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(3).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(4).JPG?raw=true)
