# Microservices System - Java + Spring Boot + Docker

## Overview
This project demonstrates a simple microservices-based system built using **Java**, **Spring Boot**, and **Docker**.  
It consists of multiple services:
- **User Service**: Manages users (CRUD).
- **Order Service**: Manages orders and communicates with User Service.
- **Product Service**: Manages products.

---

## Prerequisites
Before running the project, ensure you have the following installed:
- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [Docker](https://www.docker.com/) (optional for containerization)
- [Postman](https://www.postman.com/) (for testing APIs)
- MySQL database running locally or in Docker.

---

## Database Setup
1. Start MySQL locally or via Docker:
   ```bash
   docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=vestige -p 3306:3306 -d mysql:8
   ```

2. Update each service's `application.properties` (or `application.yml`):
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/vestige?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root
   spring.jpa.hibernate.ddl-auto=update
   ```

---

## Build Instructions
Navigate to each service directory and build using Maven:
```bash
mvn clean install
```

---

## Running the Services
From **Spring Tool Suite (STS) / IntelliJ / Eclipse**:
- Right-click each service's main class (`UserServiceApplication.java`, `OrderServiceApplication.java`, `ProductServiceApplication.java`) → `Run As → Spring Boot App`.

Or run via CLI:
```bash
cd user-service
mvn spring-boot:run

cd order-service
mvn spring-boot:run

cd product-service
mvn spring-boot:run
```

---

## Service Ports
- **User Service** → `http://localhost:8081`
- **Order Service** → `http://localhost:8082`
- **Product Service** → `http://localhost:8083`

---

## Testing with Postman

### User Service
- **Create User (POST)** → `http://localhost:8081/users`
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com"
  }
  ```
- **Get User (GET)** → `http://localhost:8081/users/1`

### Order Service
- **Create Order (POST)** → `http://localhost:8082/orders`
  ```json
  {
    "productId": 101,
    "userId": 1,
    "quantity": 2
  }
  ```
  > Order Service will call User Service to verify the user.

- **Get Order (GET)** → `http://localhost:8082/orders/1`

### Product Service
- **Create Product (POST)** → `http://localhost:8083/products`
  ```json
  {
    "name": "Laptop",
    "price": 75000
  }
  ```
- **Get Product (GET)** → `http://localhost:8083/products/1`

---

## API Documentation
Each service has Swagger UI enabled:
- User Service → `http://localhost:8081/swagger-ui.html`
- Order Service → `http://localhost:8082/swagger-ui.html`
- Product Service → `http://localhost:8083/swagger-ui.html`

---

## Health Checks
Spring Boot actuator endpoints are available:
```http
http://localhost:8081/actuator/health
http://localhost:8082/actuator/health
http://localhost:8083/actuator/health
```

---

## Running with Docker
1. Build Docker images for each service:
   ```bash
   mvn spring-boot:build-image -DskipTests
   ```
2. Start containers:
   ```bash
   docker run -p 8081:8081 user-service
   docker run -p 8082:8082 order-service
   docker run -p 8083:8083 product-service
   ```

---

## Next Steps
- Add API Gateway (e.g., Spring Cloud Gateway).
- Register services in Eureka for discovery.
- Secure APIs using JWT authentication.

---
