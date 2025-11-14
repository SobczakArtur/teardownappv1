# 🔧 TeardownApp‑v1

TeardownApp‑v1 is a Spring Boot application for managing electronic substrates.  
This version does not include authentication or authorization, making it simple to run and test.

# ⚙️ Getting Started
1️⃣ Clone the repository

git clone https://github.com/SobczakArtur/teardownappv1.git  
cd teardownappv1

2️⃣ Build and run with Maven

./mvnw spring-boot:run


Or package and run the JAR:  

./mvnw clean package  
java -jar target/teardownappv1-0.0.1-SNAPSHOT.jar  

3️⃣ Run with Docker (Optional)

You can run the app along with MySQL using Docker Compose:

docker-compose up --build


Application will be available at:  
http://localhost:8080

# 🗂️ Database

Uses MySQL for data persistence.

# 📦 API Endpoints

You can test the endpoints using Postman or any HTTP client.


| Method | Endpoint | Description |
|--------|----------|-------------|
| GET |	/api/substrate |	List all substrates |
| GET |	/api/substrate/{id} |	Get substrate by ID |
| POST	| /api/substrate |	Create new substrate |
| PUT |	/api/substrate/{id} |	Update substrate |
| PATCH |	/api/substrate/{id} |	Partially update substrate |
| DELETE | /api/substrate/{id} |	Delete substrate |

---

| Layer | Technology |
|-------|-------------|
| Backend | Java 17, Spring Boot |
| Security | Spring Security, JWT |
| Database | MySQL, H2 (for tests) |
| Tools | Docker, Docker Compose |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito, MockMvc |

---


All endpoints are publicly accessible (no authentication required).

# 🧪 Testing

Unit tests are included.

Run tests with:

./mvnw test

# 📝 License

This project is licensed under the MIT License.
See the full license in the [LICENSE](./LICENSE) file.

<br><br>
**Example of CRUD operations:**

![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(1).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(2).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(3).JPG?raw=true)
<br><br>
![](https://github.com/SobczakArtur/teardownappv1/blob/master/images/teardown_app%20(4).JPG?raw=true)
