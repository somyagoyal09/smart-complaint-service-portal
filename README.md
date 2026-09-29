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

A full-stack web application that connects customers, engineers and administrators
through a centralized complaint registration, assignment, tracking and resolution workflow.

</div>

---

## 💡 What is Smart Complaint & Service Management Portal?

**Smart Complaint & Service Management Portal** is a full-stack complaint and
service management application developed using Java and Spring Boot.

The system provides a centralized platform where customers can register and
track complaints, engineers can manage assigned complaints and update their
resolution status, and administrators can assign complaints and monitor
overall service activity.

The application implements a structured complaint lifecycle with
role-based access control, JWT-secured REST APIs, file attachments,
complaint conversations and administrative analytics.

---

## ✨ Key Features

- **Complaint Registration** — Customers can raise complaints with title, description, category and priority.
- **Complaint Tracking** — Customers can view their complaints and monitor their current status.
- **Engineer Assignment** — Administrators can assign complaints to engineers.
- **Complaint Lifecycle** — Complaints move through `OPEN → IN_PROGRESS → RESOLVED → CLOSED`.
- **Role-Based Access Control** — Separate access and operations for Customers, Engineers and Admins.
- **JWT Authentication** — Stateless authentication using JSON Web Tokens.
- **Secure Password Storage** — Passwords are stored using BCrypt password hashing.
- **Complaint Conversations** — Customers and engineers can communicate through complaint-specific comments.
- **File Attachments** — Complaint-related files can be uploaded and downloaded through secured endpoints.
- **Admin Analytics** — Administrators can view complaint statistics and service activity.
- **REST APIs** — Application functionality is exposed through RESTful endpoints.
- **Swagger / OpenAPI** — Interactive API documentation for exploring and testing endpoints.
- **Validation & Exception Handling** — Request validation and centralized exception handling for API errors.
- **Unit Testing** — Service-layer and application-context tests are included.

---

## 👥 User Roles

| Role | Capabilities |
|------|-------------|
| **Customer** | Register, login, raise complaints, view own complaints, upload attachments, communicate with engineers, close or reopen eligible complaints |
| **Engineer** | View assigned complaints, start work, update complaint status, resolve complaints and communicate with customers |
| **Admin** | View complaints, assign engineers, manage complaint status and view analytics |

---

## 🔄 Complaint Lifecycle

<pre>
                         CUSTOMER
                            │
                            ▼
                    Raise Complaint
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
                            │
                     ┌──────┴──────┐
                     │             │
                  Reopen        Remain Closed
                     │
                     ▼
                 IN_PROGRESS
</pre>

The complaint state transitions are validated in the backend to prevent
invalid status changes.

---

## 🖼️ Screenshots

### Login

<p align="center">
  <img src="docs/screenshots/login.png" alt="Smart Complaint Portal Login" width="100%"/>
</p>

### Customer Dashboard

<p align="center">
  <img src="docs/screenshots/customer-dashboard.png" alt="Customer Dashboard" width="100%"/>
</p>

<details>
<summary><strong>View More Screenshots</strong></summary>

### Raise a New Complaint

<p align="center">
  <img src="docs/screenshots/raise-complaint.png" alt="Raise New Complaint" width="100%"/>
</p>

### My Complaints

<p align="center">
  <img src="docs/screenshots/my-complaints.png" alt="My Complaints" width="100%"/>
</p>

### Customer Profile

<p align="center">
  <img src="docs/screenshots/profile.png" alt="Customer Profile" width="100%"/>
</p>

</details>

---

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
                              ┌────────────────┼────────────────┐
                              ▼                ▼                ▼
                           Users           Complaints      Comments /
                                                              Attachments
</pre>

---

## 🔐 Security Architecture

The application uses **Spring Security** with stateless **JWT-based
authentication** and role-based authorization.

<pre>
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
Frontend Sends Bearer Token
    │
    ▼
JWT Authentication Filter
    │
    ▼
Spring Security Context
    │
    ▼
Role-Based Authorization
    │
    ├── CUSTOMER
    ├── ENGINEER
    └── ADMIN
</pre>

Security features include:

- Stateless JWT authentication
- BCrypt password hashing
- Method-level authorization using Spring Security
- Customer ownership checks
- Engineer assignment-based access
- Protected REST endpoints
- Environment-based configuration for database and JWT secrets

---

## 🧩 Technology Stack

| Layer | Technology |
|-------|-----------|
| Programming Language | Java 21 |
| Backend Framework | Spring Boot 3.4.1 |
| Web / REST | Spring Web |
| Security | Spring Security |
| Authentication | JWT |
| Persistence | Spring Data JPA |
| ORM | Hibernate |
| Database | MySQL |
| Validation | Jakarta Bean Validation |
| API Documentation | SpringDoc OpenAPI / Swagger UI |
| Frontend | HTML5, CSS3, Vanilla JavaScript |
| Build Tool | Maven |
| Testing | JUnit / Spring Boot Test |
| Utility | Lombok |

---

## 📁 Directory Layout

<pre>
Smart-Complaint-Service-Management-Portal/
│
├── database/
│   └── seed.sql                         # Database seed data
│
├── src/
│   ├── main/
│   │   ├── java/com/wipro/smart_complaint_portal/
│   │   │
│   │   ├── config/
│   │   │   ├── DataInitializer.java     # Demo data initialization
│   │   │   ├── SecurityConfig.java      # Spring Security configuration
│   │   │   └── openApiConfig.java       # OpenAPI configuration
│   │   │
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── ComplaintController.java
│   │   │   ├── UserController.java
│   │   │   ├── CommentController.java
│   │   │   └── AttachmentController.java
│   │   │
│   │   ├── dto/                         # Request / Response DTOs
│   │   ├── entity/                      # JPA entities
│   │   ├── enums/                       # Status and priority enums
│   │   ├── exception/                   # Exception handling
│   │   ├── repository/                  # Spring Data JPA repositories
│   │   ├── security/                    # JWT filter and security utilities
│   │   └── service/                     # Business logic
│   │       └── impl/                    # Service implementations
│   │
│   ├── resources/
│   │   ├── static/                      # Frontend pages and JavaScript
│   │   │   ├── css/
│   │   │   ├── js/
│   │   │   ├── index.html
│   │   │   ├── register.html
│   │   │   ├── dashboard.html
│   │   │   ├── complaint-details.html
│   │   │   ├── engineer-dashboard.html
│   │   │   └── admin-dashboard.html
│   │   │
│   │   ├── application.properties       # Application configuration
│   │   └── data.sql                     # Initial SQL data
│   │
│   └── test/
│       └── java/                        # Unit and application tests
│
├── pom.xml                              # Maven dependencies and build config
├── mvnw                                  # Maven Wrapper
├── mvnw.cmd                              # Maven Wrapper for Windows
└── README.md
</pre>

---

## ⚙️ Setup & Installation

### Prerequisites

Install the following before running the project:

- **Java 21**
- **MySQL 8+**
- **Maven 3.9+** or use the included Maven Wrapper

### 🔧 Configure MySQL

Create the database:

```sql
CREATE DATABASE smart_complaint_db;
