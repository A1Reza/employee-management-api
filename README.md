# 👨‍💼 Employee Management API

\<p align="center">   A RESTful API for managing employee information, built with Spring Boot. \</p>

\<p align="center">   \<img src="[https://img.shields.io/badge/Java-21-orange?logo=openjdk](https://img.shields.io/badge/Java-21-orange?logo=openjdk)" />   \<img src="[https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen?logo=springboot](https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen?logo=springboot)" />   \<img src="[https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)" />   \<img src="[https://img.shields.io/badge/Database-H2-lightgrey?logo=h2](https://img.shields.io/badge/Database-H2-lightgrey?logo=h2)" /> \</p>

## 📖 Overview

Employee Management API is a Spring Boot RESTful application designed to manage employee records. It provides CRUD operations, employee search capabilities, data validation, database persistence, and endpoint security.

The project follows a layered architecture with separate Controller, Service, Repository, DTO, Model, and Configuration packages.

## ✨ Features

- 👤 Create and retrieve employee records
- 📋 Get all employees and find employees by ID
- 🔍 Search employees by first name
- 🏢 Filter employees by department
- 📧 Find employees by email using custom JPQL
- 🗑️ Delete employee records
- ✅ Request validation using Jakarta Bean Validation
- ⚠️ Global exception handling with custom validation messages
- 🔐 HTTP Basic Authentication with Spring Security
- 🗄️ H2 in-memory database with Spring Data JPA
- 📚 Interactive API documentation with Swagger UI

## 🛠️ Technologies

| Technology              | Purpose                          |
| ----------------------- | -------------------------------- |
| Java 21                 | Programming language             |
| Spring Boot             | Application framework            |
| Spring Web              | REST API development             |
| Spring Data JPA         | Data persistence                 |
| Hibernate               | ORM                              |
| H2 Database             | In-memory database               |
| Spring Security         | Authentication and authorization |
| Jakarta Bean Validation | Input validation                 |
| SpringDoc OpenAPI       | API documentation                |
| Lombok                  | Boilerplate code reduction       |
| Maven                   | Dependency management and build  |

## 📁 Project Structure

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

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven
- IntelliJ IDEA or another Java IDE

### Installation

1. Clone the repository:

```bash
git clone https://github.com/A1Reza/employee-management-api.git
```

2. Navigate to the project directory:

```bash
cd employee-management-api
```

3. Run the application:

```bash
mvn spring-boot:run
```

The application runs at:

```text
http://localhost:8083
```

## 🔗 API Endpoints

Base URL: `http://localhost:8083`

| Method | Endpoint                                   | Description             |
| ------ | ------------------------------------------ | ----------------------- |
| GET    | `/employees`                               | Retrieve all employees  |
| GET    | `/employees/{id}`                          | Retrieve employee by ID |
| POST   | `/employees`                               | Create a new employee   |
| DELETE | `/employees/{id}`                          | Delete an employee      |
| GET    | `/employees/search?firstName=Ali`          | Search by first name    |
| GET    | `/employees/byDepartment?department=IT`    | Filter by department    |
| GET    | `/employees/byEmail?email=ali@example.com` | Search by email         |

## 📝 Example Request

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

## 🔐 Security

The application uses Spring Security with HTTP Basic Authentication.

Example credentials for the configured development environment:

```text
Username: postgres
Password: ROOT
```

For protected endpoints, Basic Authentication requires a Base64-encoded value of:

```text
username:password
```

You can encode this value using:

🔗 [Base64Encode.net](https://www.base64encode.net/)

Example:

```text
postgres:ROOT
```

Encoded value:

```text
cG9zdGdyZXM6Uk9PVA==
```

Use it in the HTTP request header:

```http
Authorization: Basic cG9zdGdyZXM6Uk9PVA==
```

**Note:** Base64 is encoding, not encryption. Do not use Basic Authentication over an unencrypted connection in production. The credentials above are development examples; use secure environment-based credentials in real deployments.

## 📚 API Documentation — Swagger

Interactive API documentation is available at:

[http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

## 🗄️ H2 Database Console

The H2 database console is available at:

[http://localhost:8083/h2-console](http://localhost:8083/h2-console)

Connection settings:

| Setting  | Value                    |
| -------- | ------------------------ |
| JDBC URL | `jdbc:h2:mem:employeedb` |
| Username | `sa`                     |
| Password | Leave empty              |

Example SQL query:

```sql
SELECT * FROM EMPLOYEE;
```

## 🧪 Testing

API endpoints can be tested using:

- IntelliJ IDEA HTTP Client (`api-requests.http`)
- Postman
- Swagger UI

The project includes request validation scenarios and authentication tests.

## 👨‍💻 Author

**Reza Asadipour**

GitHub: [@A1Reza](https://github.com/A1Reza)

---

\<p align="center">
&#x20; Built with ☕ and Spring Boot
\</p>
