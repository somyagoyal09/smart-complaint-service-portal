<div align="center">

# Smart Complaint & Service Management Portal

<p>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.4.1-6DB33F?style=flat-square&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white" />
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white" />
  <img src="https://img.shields.io/badge/REST%20API-02569B?style=flat-square" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white" />
</p>

### Smart Complaint & Service Management

**From complaint registration to resolution.**

A full-stack complaint management portal that connects customers,
engineers and administrators through a centralized workflow for
complaint registration, assignment, tracking and resolution.

</div>

## 💡 What is Smart Complaint & Service Management Portal?

**Smart Complaint & Service Management Portal** is a full-stack web application designed to streamline the customer complaint and service-request process.

The system provides a centralized platform where customers can raise and track complaints, engineers can manage assigned complaints and update their resolution status, and administrators can manage complaints, assign engineers and monitor overall service activity.

The application implements a complete complaint lifecycle with role-based access control, JWT-secured REST APIs, file attachments, support conversations and administrative analytics.

## ✨ Key Features

* **Complaint Management** — Customers can submit complaints with priority and relevant details.
* **Complaint Lifecycle** — Manage complaints through `OPEN → IN_PROGRESS → RESOLVED → CLOSED`.
* **Engineer Assignment** — Administrators can assign complaints to available engineers.
* **Role-Based Access Control** — Separate capabilities for Customers, Engineers and Administrators.
* **JWT Authentication** — Stateless authentication using JSON Web Tokens.
* **Secure Password Storage** — Passwords are protected using BCrypt hashing.
* **Complaint Conversations** — Customers and engineers can communicate through complaint-specific comments.
* **File Attachments** — Upload and access files associated with complaints.
* **Admin Analytics** — View complaint statistics and service activity through the admin dashboard.
* **REST API** — Backend functionality is exposed through RESTful endpoints.
* **Swagger / OpenAPI** — Interactive API documentation for testing and exploring endpoints.
* **Exception Handling** — Centralized handling of application and resource-related errors.

## 👥 User Roles

| Role         | Capabilities                                                                                                                             |
| ------------ | ---------------------------------------------------------------------------------------------------------------------------------------- |
| **Customer** | Register, login, submit complaints, view complaints, upload attachments, communicate with engineers, close or reopen eligible complaints |
| **Engineer** | View assigned complaints, start work, update complaint status, resolve complaints and communicate with customers                         |
| **Admin**    | View complaints, assign engineers, manage complaint status and monitor analytics                                                         |

## 🔄 Complaint Lifecycle

```text
                    CUSTOMER
                       │
                       ▼
               Submit Complaint
                       │
                       ▼
                    OPEN
                       │
              Engineer Starts Work
                       │
                       ▼
                 IN_PROGRESS
                       │
              Engineer Resolves
                       │
                       ▼
                  RESOLVED
                       │
               Customer Accepts
                       │
                       ▼
                    CLOSED
```

The complaint status transitions are validated on the backend to prevent invalid state changes.

## 🖼️ Screenshots

### Customer Dashboard

<p align="center">
  <img src="docs/screenshots/customer-dashboard.png" alt="Customer Dashboard" width="100%"/>
</p>

<details>
<summary><strong>View More Screenshots</strong></summary>

### Login

<p align="center">
  <img src="docs/screenshots/login.png" alt="Login" width="100%"/>
</p>

### Customer Registration

<p align="center">
  <img src="docs/screenshots/register.png" alt="Customer Registration" width="100%"/>
</p>

### Customer Dashboard

<p align="center">
  <img src="docs/screenshots/customer-dashboard.png" alt="Customer Dashboard" width="100%"/>
</p>

### Complaint Details

<p align="center">
  <img src="docs/screenshots/complaint-details.png" alt="Complaint Details" width="100%"/>
</p>

### Engineer Dashboard

<p align="center">
  <img src="docs/screenshots/engineer-dashboard.png" alt="Engineer Dashboard" width="100%"/>
</p>

### Admin Dashboard

<p align="center">
  <img src="docs/screenshots/admin-dashboard.png" alt="Admin Dashboard" width="100%"/>
</p>

</details>

## 🏗️ Architecture Overview

<pre>
                         SMART COMPLAINT PORTAL
                                  │
                     ┌────────────┴────────────┐
                     │                         │
                     ▼                         ▼
                Frontend                  REST API Layer
             HTML / CSS / JS              Spring Boot
                     │                         │
                     │              ┌──────────┼──────────┐
                     │              ▼          ▼          ▼
                     │         Controller   Service   Security
                     │              │          │          │
                     │              │          │       JWT / RBAC
                     │              │          │
                     │              └──────────┼──────────┘
                     │                         ▼
                     │                    Repository
                     │                         │
                     │                    JPA / Hibernate
                     │                         │
                     └─────────────────────────┤
                                               ▼
                                             MySQL
                                               │
                         ┌─────────────────────┼─────────────────────┐
                         ▼                     ▼                     ▼
                     Users                 Complaints           Comments /
                                                                  Attachments
</pre>

## 🔐 Security Architecture

The application uses Spring Security with JWT-based authentication.

```text
User Login
    │
    ▼
Authentication API
    │
    ▼
Credentials Validation
    │
    ▼
JWT Token Generated
    │
    ▼
Frontend Stores Token
    │
    ▼
Bearer Token → REST API
    │
    ▼
JWT Authentication Filter
    │
    ▼
Role-Based Authorization
    │
    ├── CUSTOMER
    ├── ENGINEER
    └── ADMIN
```

Security includes:

* Stateless JWT authentication
* BCrypt password hashing
* Role-based authorization
* Method-level access control
* Protected REST endpoints
* Ownership checks for customer complaints
* Assignment-based access for engineers

## 🧩 Technology Stack

| Layer             | Technology                      |
| ----------------- | ------------------------------- |
| Language          | Java 21                         |
| Backend           | Spring Boot 3.4.1               |
| Web Layer         | Spring Web / REST               |
| Security          | Spring Security + JWT           |
| Persistence       | Spring Data JPA + Hibernate     |
| Database          | MySQL                           |
| Validation        | Jakarta Bean Validation         |
| API Documentation | SpringDoc OpenAPI / Swagger UI  |
| Frontend          | HTML5, CSS3, Vanilla JavaScript |
| Build Tool        | Maven                           |
| Testing           | JUnit / Spring Boot Test        |

## 📁 Directory Layout

<pre>
Smart-Complaint-Service-Management-Portal/
│
├── database/
│   └── seed.sql
│
├── src/
│   ├── main/
│   │   ├── java/com/wipro/smart_complaint_portal/
│   │   │
│   │   ├── config/
│   │   │   ├── DataInitializer.java
│   │   │   ├── SecurityConfig.java
│   │   │   └── openApiConfig.java
│   │   │
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── ComplaintController.java
│   │   │   ├── UserController.java
│   │   │   ├── CommentController.java
│   │   │   └── AttachmentController.java
│   │   │
│   │   ├── dto/
│   │   │   └── Request / Response DTOs
│   │   │
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   ├── Role.java
│   │   │   ├── Complaint.java
│   │   │   ├── Comment.java
│   │   │   ├── Attachment.java
│   │   │   └── ComplaintStatusHistory.java
│   │   │
│   │   ├── enums/
│   │   │   ├── ComplaintStatus.java
│   │   │   └── Priority.java
│   │   │
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── ResourceNotFoundException.java
│   │   │   └── DuplicateResourceException.java
│   │   │
│   │   ├── repository/
│   │   │   └── Spring Data JPA repositories
│   │   │
│   │   ├── security/
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   └── CustomUserDetailsService.java
│   │   │
│   │   └── service/
│   │       ├── interfaces
│   │       └── implementations
│   │
│   ├── main/resources/
│   │   ├── static/
│   │   │   ├── index.html
│   │   │   ├── register.html
│   │   │   ├── dashboard.html
│   │   │   ├── complaint-details.html
│   │   │   ├── engineer-dashboard.html
│   │   │   ├── admin-dashboard.html
│   │   │   ├── css/
│   │   │   └── js/
│   │   │
│   │   ├── application.properties
│   │   └── data.sql
│   │
│   └── test/
│       └── java/
│           └── Unit and application tests
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
</pre>

## ⚙️ Setup & Installation

### Prerequisites

Make sure the following are installed:

* **Java 21**
* **MySQL 8+**
* **Maven 3.9+** or use the included Maven Wrapper

### 🔧 Configure the Database

Create a MySQL database:

```sql
CREATE DATABASE smart_complaint_db;
```

Configure the database connection using environment variables:

```text
DB_URL=jdbc:mysql://localhost:3306/smart_complaint_db
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_secure_secret
```

> **Note:** Do not commit private credentials or production secrets to GitHub.

### 📥 Clone the Repository

```bash
git clone https://github.com/somyagoyal09/smart-complaint-service-portal.git
cd smart-complaint-service-portal
```

### ▶️ Run the Application

Using Maven Wrapper:

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

The application starts at:

```text
http://localhost:8080
```

## 📖 API Documentation

Once the application is running, Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/v3/api-docs
```

## 🧪 Testing

The project includes automated tests using Spring Boot's testing framework and JUnit.

Run the test suite with:

```bash
mvnw.cmd test
```

or:

```bash
./mvnw test
```

## 🔗 Project Links

💻 **GitHub Repository:** https://github.com/somyagoyal09/smart-complaint-service-portal

📖 **API Documentation:** `http://localhost:8080/swagger-ui/index.html`

## 🎓 Wipro TalentNext Project

This project was developed as part of the **Wipro TalentNext 2026–27 training project**.

The system addresses the assigned **Smart Complaint & Service Management Portal** requirements by providing:

* Customer complaint registration
* Complaint tracking
* Engineer assignment
* Complaint status management
* Administrative monitoring
* REST API integration
* Role-based access control
* Centralized complaint management

## 📜 License

This project is intended for educational and training purposes.

<div align="center">

**Smart Complaint & Service Management Portal — From complaint registration to resolution.**

</div>
