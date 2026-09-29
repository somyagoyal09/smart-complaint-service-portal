<div align="center">

# Smart Complaint & Service Management Portal

<p>

<img src="https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white" />

<img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=flat-square&logo=springboot&logoColor=white" />

<img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white" />

<img src="https://img.shields.io/badge/Hibernate-7.4.5-59666C?style=flat-square&logo=hibernate&logoColor=white" />

<img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white" />

<img src="https://img.shields.io/badge/JWT-000000?style=flat-square&logo=jsonwebtokens&logoColor=white" />

<img src="https://img.shields.io/badge/REST%20API-02569B?style=flat-square" />

<img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white" />

</p>

### Smart Complaint & Service Management

**From complaint registration to resolution.**

A full-stack web application that connects customers, engineers and administrators through a centralized complaint registration, assignment, tracking and resolution workflow.

</div>

---

## 💡 What is Smart Complaint & Service Management Portal?

**Smart Complaint & Service Management Portal** is a full-stack complaint and service management application developed using Java and Spring Boot.

The system provides a centralized platform where customers can register and track complaints, engineers can manage assigned complaints and update their resolution status, and administrators can assign complaints and monitor overall service activity.

The application implements a structured complaint lifecycle with role-based access control, JWT-secured REST APIs, file attachments, complaint conversations and administrative analytics.

---

## ✨ Key Features

- **Complaint Registration** — Customers can raise complaints with title, description, category and priority.
- **Complaint Tracking** — Customers can view their complaints and monitor their current status.
- **Engineer Assignment** — Administrators can assign complaints to engineers.
- **Complaint Lifecycle** — Complaints move through `OPEN → IN_PROGRESS → RESOLVED → CLOSED`.
- **Role-Based Access Control** — Separate access and operations for Customers, Engineers and Admins.
- **JWT Authentication** — Stateless authentication using JSON Web Tokens.
- **Secure Password Storage** — Passwords are protected using BCrypt password hashing.
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
                  Reopen      Remain Closed
                     │
                     ▼
                 IN_PROGRESS

</pre>

The complaint state transitions are validated in the backend to prevent invalid status changes.

---

## 🖼️ Screenshots

### Login Page

<p align="center">
<img src="docs/screenshots/login_page.png" alt="Login Page" width="85%"/>
</p>

### Account Creation

<p align="center">
<img src="docs/screenshots/account_creation_page.png" alt="Account Creation Page" width="85%"/>
</p>

### Customer Dashboard

<p align="center">
<img src="docs/screenshots/dashboard.png" alt="Customer Dashboard" width="85%"/>
</p>

### Raise Complaint

<p align="center">
<img src="docs/screenshots/raise_complaint.png" alt="Raise Complaint" width="85%"/>
</p>

### Complaint Section

<p align="center">
<img src="docs/screenshots/complaint_section.png" alt="Complaint Section" width="85%"/>
</p>

### Create Complaint

<p align="center">
<img src="docs/screenshots/create_complaint.png" alt="Create Complaint" width="85%"/>
</p>

### Profile

<p align="center">
<img src="docs/screenshots/profile.png" alt="Profile Page" width="85%"/>
</p>

---

## 🏗️ Architecture Overview

<pre>

                         SMART COMPLAINT PORTAL
                                  │
                   ┌──────────────┴──────────────┐
                   │                             │
                   ▼                             ▼
              Frontend                    REST API Layer
           HTML / CSS / JS                  Spring Boot
                                                 │
                              ┌──────────────────┼──────────────────┐
                              ▼                  ▼                  ▼
                         Controller          Service           Security
                              │                  │              JWT / RBAC
                              │                  │                  │
                              └──────────────────┼──────────────────┘
                                                 ▼
                                            Repository
                                                 │
                                           JPA / Hibernate
                                                 │
                                                 ▼
                                               MySQL
                                                 │
                              ┌──────────────────┼──────────────────┐
                              ▼                  ▼                  ▼
                           Users             Complaints       Comments /
                                                               Attachments

</pre>

---

## 🔐 Security Architecture

The application uses **Spring Security** with stateless **JWT-based authentication** and role-based authorization.

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
- Environment-based configuration for sensitive database credentials

---

## 🧩 Technology Stack

| Layer | Technology |
|-------|-----------|
| Programming Language | Java 17 |
| Backend Framework | Spring Boot 4.1.1 |
| Web / REST | Spring Web |
| Security | Spring Security |
| Authentication | JWT |
| Persistence | Spring Data JPA |
| ORM | Hibernate 7.4.5 |
| Database | MySQL 8.0 |
| Validation | Jakarta Bean Validation |
| API Documentation | SpringDoc OpenAPI / Swagger UI |
| Frontend | HTML5, CSS3, Vanilla JavaScript |
| Build Tool | Maven |
| Testing | JUnit / Spring Boot Test |
| Utility | Lombok |

---

## 📁 Directory Layout

<pre>

smart-complaint-service-portal/
│
├── docs/
│   └── screenshots/
│       ├── login_page.png
│       ├── account_creation_page.png
│       ├── dashboard.png
│       ├── raise_complaint.png
│       ├── complaint_section.png
│       ├── create_complaint.png
│       └── profile.png
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/miet/complaintportal/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       └── service/
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

</pre>

---

## ⚙️ Setup & Installation

Follow these steps to clone, configure and run the project locally.

### 1. Prerequisites

Make sure the following are installed:

- **Java 17**
- **MySQL 8.0+**
- **Git**
- **VS Code / IntelliJ IDEA**

Maven does not need to be installed separately because the project includes the Maven Wrapper.

Verify Java:

```bash
java -version