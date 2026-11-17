# 👨‍💼 Employee Management API

<p align="center">
  <em>A RESTful API for managing employee information, built with Spring Boot.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen?logo=springboot" alt="Spring Boot 4.0" />
  <img src="https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven" alt="Maven" />
  <img src="https://img.shields.io/badge/Database-H2-lightgrey?logo=h2" alt="H2 Database" />
</p>

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#️-tech-stack)
- [Architecture](#️-architecture)
- [Getting Started](#-getting-started)
- [Configuration](#️-configuration)
- [API Endpoints](#-api-endpoints)
- [Request / Response Examples](#-request--response-examples)
- [Validation Rules](#-validation-rules)
- [Error Handling](#️-error-handling)
- [Security](#-security)
- [Swagger UI](#-swagger-ui)
- [H2 Console](#️-h2-console)
- [Testing](#-testing)
- [Roadmap](#-roadmap)
- [Author](#-author)

---

## 📖 Overview

**Employee Management API** is a Spring Boot RESTful application designed to manage
employee records. It provides CRUD operations, employee search capabilities, request
validation, database persistence, and endpoint security.

The project follows a **layered architecture** with separate `Controller`, `Service`,
`Repository`, `DTO`, `Model`, and `Configuration` packages.

---

## ✨ Features

- 👤 Create and retrieve employee records
- 📋 List all employees / find by ID
- 🔍 Search employees by first name
- 🏢 Filter employees by department
- 📧 Find employees by email using custom JPQL query
- 🗑️ Delete employee records
- ✅ Request validation using Jakarta Bean Validation
- ⚠️ Global exception handling with custom validation messages
- 🔐 HTTP Basic Authentication with Spring Security
- 🗄️ H2 in-memory database with Spring Data JPA
- 📚 Interactive API documentation with Swagger UI

---

## 🛠️ Tech Stack

| Technology              | Purpose                          |
| ----------------------- | -------------------------------- |
| Java 21                 | Programming language             |
| Spring Boot             | Application framework            |
| Spring Web              | REST API development             |
| Spring Data JPA         | Data persistence                 |
| Hibernate               | ORM                              |
| H2 Database             | In-memory database               |
| Spring Security         | Authentication & authorization   |
| Jakarta Bean Validation | Input validation                 |
| SpringDoc OpenAPI       | API documentation                |
| Lombok                  | Boilerplate code reduction       |
| Maven                   | Dependency management and build  |

---

## 🏗️ Architecture

```text
src/main/java/com/employees
│
├── config
│   ├── SecurityConfig.java
│   └── GlobalExceptionHandler.java
│
├── controller
│   ├── EmployeeController.java
│   └── SecurityTestController.java
│
├── dto
│   └── EmployeeDto.java
│
├── model
│   └── Employee.java
│
├── repository
│   └── EmployeeRepository.java
│
└── service
    └── EmployeeService.java
```

**Request flow:** `Client → Controller → Service → Repository → H2 Database`

---

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.9+** (or use the included `mvnw` wrapper)
- IntelliJ IDEA or any Java IDE (optional)

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/A1Reza/employee-management-api.git
   cd employee-management-api
   ```

2. **Build the project**

   ```bash
   ./mvnw clean install
   ```

3. **Run the application**

   ```bash
   ./mvnw spring-boot:run
   ```

   Or run the generated JAR:

   ```bash
   java -jar target/employee-management-api.jar
   ```

The application will be available at:

```text
http://localhost:8083
```

---

## ⚙️ Configuration

Key properties (`application.properties` / `application.yml`):

| Property                          | Default                    | Description                    |
| --------------------------------- | -------------------------- | ------------------------------ |
| `server.port`                     | `8083`                     | HTTP server port               |
| `spring.datasource.url`           | `jdbc:h2:mem:employeedb`   | H2 JDBC URL                    |
| `spring.datasource.username`      | `sa`                       | Database username              |
| `spring.datasource.password`      | *(empty)*                  | Database password              |
| `spring.jpa.hibernate.ddl-auto`   | `update`                   | Schema generation strategy     |

**Security credentials** should be supplied via environment variables:

| Variable              | Default     | Description              |
| --------------------- | ----------- | ------------------------ |
| `APP_SECURITY_USER`   | `postgres`  | Basic Auth username      |
| `APP_SECURITY_PASS`   | `ROOT`      | Basic Auth password      |

> ⚠️ Never commit real credentials to source control.

---

## 🔗 API Endpoints

**Base URL:** `http://localhost:8083`

| Method | Endpoint                                   | Description              | Auth |
| ------ | ------------------------------------------ | ------------------------ | :--: |
| GET    | `/employees`                               | Retrieve all employees   | 🔒   |
| GET    | `/employees/{id}`                          | Retrieve employee by ID  | 🔒   |
| POST   | `/employees`                               | Create a new employee    | 🔒   |
| DELETE | `/employees/{id}`                          | Delete an employee       | 🔒   |
| GET    | `/employees/search?firstName=Ali`          | Search by first name     | 🔒   |
| GET    | `/employees/byDepartment?department=IT`    | Filter by department     | 🔒   |
| GET    | `/employees/byEmail?email=ali@example.com` | Search by email (JPQL)   | 🔒   |

**HTTP status codes used:**

| Code | Meaning                                              |
| ---- | ---------------------------------------------------- |
| 200  | OK – request succeeded                               |
| 201  | Created – employee successfully created              |
| 204  | No Content – employee deleted                        |
| 400  | Bad Request – validation failed                      |
| 401  | Unauthorized – missing / invalid credentials         |
| 404  | Not Found – employee does not exist                  |
| 500  | Internal Server Error                                |

---

## 📝 Request / Response Examples

### Create Employee

**POST** `/employees`

```json
{
  "firstName": "Ali",
  "lastName": "Ahmadi",
  "email": "ali@example.com",
  "department": "IT",
  "salary": 65000.0
}
```

**Response `201 Created`:**

```json
{
  "id": 1,
  "firstName": "Ali",
  "lastName": "Ahmadi",
  "email": "ali@example.com",
  "department": "IT",
  "salary": 65000.0
}
```

### Get Employee by ID

**GET** `/employees/1` → `200 OK`

### Search by First Name

**GET** `/employees/search?firstName=Ali` → `200 OK`

```json
[
  {
    "id": 1,
    "firstName": "Ali",
    "lastName": "Ahmadi",
    "email": "ali@example.com",
    "department": "IT",
    "salary": 65000.0
  }
]
```

### Delete Employee

**DELETE** `/employees/1` → `204 No Content`

---

## ✅ Validation Rules

| Field        | Constraint                                     |
| ------------ | ---------------------------------------------- |
| `firstName`  | Not blank, max 50 characters                   |
| `lastName`   | Not blank, max 50 characters                   |
| `email`      | Not blank, valid email format, must be unique  |
| `department` | Not blank, max 50 characters                   |
| `salary`     | Positive number, greater than 0                |

Violations return `400 Bad Request` with field-level messages.

---

## ⚠️ Error Handling

A `GlobalExceptionHandler` (`@RestControllerAdvice`) intercepts exceptions and returns
a consistent JSON error payload.

**Example – validation failure (`400 Bad Request`):**

```json
{
  "timestamp": "2025-01-15T10:30:00Z",
  "status": 400,
  "error": "Validation Failed",
  "message": "One or more fields are invalid",
  "path": "/employees",
  "details": [
    "email: must be a well-formed email address",
    "salary: must be greater than 0"
  ]
}
```

**Example – employee not found (`404 Not Found`):**

```json
{
  "timestamp": "2025-01-15T10:31:12Z",
  "status": 404,
  "error": "Not Found",
  "message": "Employee with id 99 not found",
  "path": "/employees/99"
}
```

---

## 🔐 Security

The application uses **Spring Security with HTTP Basic Authentication**.

For protected endpoints, send the header:

```http
Authorization: Basic <base64(username:password)>
```

**Generate the Base64 value** using [Base64Encode.net](https://www.base64encode.net/):

```text
postgres:ROOT
```

Encoded value:

```text
cG9zdGdyZXM6Uk9PVA==
```

Then use it:

```http
Authorization: Basic cG9zdGdyZXM6Uk9PVA==
```

You can also encode from the command line:

```bash
echo -n "postgres:ROOT" | base64
# cG9zdGdyZXM6Uk9PVA==
```

> ⚠️ **Security note:** Base64 is *encoding*, not encryption. Never use HTTP Basic Auth
> over an unencrypted connection in production. The credentials above are development
> examples; use secure environment-based credentials in real deployments, and always
> serve behind HTTPS.

---

## 📚 Swagger UI

Interactive API documentation:

```text
http://localhost:8083/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8083/v3/api-docs
```

---

## 🗄️ H2 Console

Available at:

```text
http://localhost:8083/h2-console
```

Connection settings:

| Setting  | Value                    |
| -------- | ------------------------ |
| JDBC URL | `jdbc:h2:mem:employeedb` |
| Username | `sa`                     |
| Password | *(leave empty)*          |

Example query:

```sql
SELECT * FROM EMPLOYEE;
```

---

## 🧪 Testing

API endpoints can be exercised with:

- **IntelliJ IDEA HTTP Client** (`api-requests.http`)
- **Postman** / **Insomnia**
- **Swagger UI** (browser-based)
- **cURL**

Quick smoke test:

```bash
curl -u postgres:ROOT http://localhost:8083/employees
```

Run unit and integration tests:

```bash
./mvnw test
```

---

## 🗺️ Roadmap

- [ ] Add `PUT /employees/{id}` (full update)
- [ ] Add `PATCH /employees/{id}` (partial update)
- [ ] Add pagination & sorting (`Pageable`)
- [ ] Replace Basic Auth with JWT-based authentication
- [ ] Add role-based authorization (ADMIN / USER)
- [ ] Add Testcontainers-based integration tests
- [ ] Add Dockerfile & docker-compose
- [ ] Add CI pipeline (GitHub Actions)
- [ ] Add response caching for read-heavy endpoints

---

## 👨‍💻 Author

**Reza Asadipour**

- GitHub: [@A1Reza](https://github.com/A1Reza)
- Repository: [employee-management-api](https://github.com/A1Reza/employee-management-api)

---

<p align="center">
  Built with ☕ and Spring Boot
</p>
