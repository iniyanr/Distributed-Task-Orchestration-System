# 🚀 Distributed Task Orchestration System

A **Java-based backend platform** that allows users to **define, schedule, execute, and monitor distributed jobs** across multiple worker nodes.
Built using **Spring Boot**, **PostgreSQL**, **RabbitMQ**, and **Docker**, this project demonstrates real-world backend design concepts like **asynchronous processing**, **microservice communication**, **scheduling**, and **reliability mechanisms**.

---

## 🧩 Features

* ✅ Create and manage background jobs
* 🕒 Dynamic scheduling using Quartz
* 📩 Distributed task execution via RabbitMQ
* ♻️ Automatic retry and failure handling
* ⚡ Job status tracking and caching with Redis
* 🔐 JWT-based authentication and role management
* 🧠 Scalable microservice architecture
* 🤪 Unit + Integration testing
* 📊 Monitoring with Spring Actuator
* 💣 Dockerized deployment with Compose

---

## 🏋️‍♂️ Architecture Overview

```
+-----------------+        +-----------------+        +-----------------+
|  REST API       |  -->   |  Task Manager   |  -->   |   Message Queue |
|  (Spring Boot)  |        |  (Spring Boot)  |        |   (RabbitMQ)    |
+-----------------+        +-----------------+        +-----------------+
          |                         |
          |                         v
          |                 +-----------------+
          |                 |   Worker Node   |
          |                 |  (Executes job) |
          |                 +-----------------+
          |
          v
   +----------------+
   | PostgreSQL DB  |
   +----------------+
```

---

## 🧱 Tech Stack

| Layer          | Technology                             |
| -------------- | -------------------------------------- |
| **Language**   | Java 21                                |
| **Framework**  | Spring Boot 3                          |
| **Database**   | PostgreSQL                             |
| **Queue**      | RabbitMQ (for async job dispatch)      |
| **Cache**      | Redis                                  |
| **Auth**       | JWT (Spring Security)                  |
| **Build Tool** | Maven / Gradle                         |
| **Tests**      | JUnit 5, Mockito                       |
| **Deployment** | Docker, Docker Compose                 |
| **Monitoring** | Spring Actuator, Prometheus (optional) |

---

## ⚙️ System Modules

### 1. **Job API Service**

* Exposes REST endpoints for creating, updating, triggering jobs.
* Handles authentication and validation.
* Stores metadata in PostgreSQL.

### 2. **Task Manager**

* Responsible for scheduling and dispatching jobs.
* Uses Quartz Scheduler and publishes tasks to RabbitMQ.

### 3. **Worker Service**

* Subscribes to task queue.
* Executes job (e.g., API call or script simulation).
* Updates results and logs back to the database.

---

## 🚀 Getting Started

### 🧩 Prerequisites

Make sure you have installed:

* Java 21+
* Maven or Gradle
* Docker & Docker Compose
* PostgreSQL
* RabbitMQ
* Redis (optional)

---

### 🔧 Installation & Run

```bash
# Clone the repository
git clone https://github.com/<your-username>/distributed-task-orchestration.git
cd distributed-task-orchestration

# Run using Docker Compose
docker-compose up --build
```

The services will start on:

* **API Gateway:** [http://localhost:8080](http://localhost:8080)
* **Task Manager:** [http://localhost:8081](http://localhost:8081)
* **RabbitMQ Dashboard:** [http://localhost:15672](http://localhost:15672) (guest/guest)

---

## 📚 API Endpoints

| Method | Endpoint                 | Description                |
| ------ | ------------------------ | -------------------------- |
| `POST` | `/api/jobs`              | Create a new job           |
| `GET`  | `/api/jobs`              | List all jobs              |
| `POST` | `/api/jobs/{id}/trigger` | Manually trigger job       |
| `GET`  | `/api/jobs/{id}/status`  | Get job status             |
| `POST` | `/api/auth/login`        | Authenticate and get token |

> Use Swagger UI at:
> 🔗 `http://localhost:8080/swagger-ui/index.html`

---

## 🤪 Testing

Run all tests:

```bash
mvn test
```

* Unit tests → business logic validation
* Integration tests → covers messaging, DB, and job flow

---

## 📊 Monitoring & Observability

* Actuator endpoints: `/actuator/health`, `/actuator/metrics`
* Optional: Prometheus + Grafana integration for metrics visualization.

---

## 💣 Docker Compose Setup

```yaml
version: '3.8'
services:
  api:
    build: ./api
    ports:
      - "8080:8080"
    depends_on:
      - postgres
      - rabbitmq
  manager:
    build: ./manager
    ports:
      - "8081:8081"
    depends_on:
      - rabbitmq
      - redis
  worker:
    build: ./worker
    depends_on:
      - rabbitmq
  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: password
      POSTGRES_DB: jobs
    ports:
      - "5432:5432"
  rabbitmq:
    image: rabbitmq:3-management
    ports:
      - "5672:5672"
      - "15672:15672"
  redis:
    image: redis:latest
    ports:
      - "6379:6379"
```

---

## 🧠 Learning Highlights

This project demonstrates:

* Backend architecture design (modular + scalable)
* Message-driven async execution
* Thread management and scheduling
* Database optimization and caching
* CI/CD pipeline integration
* Container orchestration with Docker

---

## 👨‍💻 Author

**Iniyan**
📧 [LinkedIn](https://linkedin.com/in/iniyan-r)
💻 [GitHub](https://github.com/iniyanr)

---

## 🏁 License

This project is licensed under the MIT License — feel free to use and adapt it.
