# Access Control System

A system designed to manage access to buildings in small and medium-sized organizations. Supports user registration, building and door management, access card holders, and event logging.

## Documentation

- [API Swagger UI](https://accescontrolsystem.onrender.com/swagger-ui.html)  

---

## Tech Stack

- Java 17, Spring Boot 3.4.4  
- MongoDB Atlas  
- Spring Security  
- Spring Mail (SMTP via Gmail)  
- ModelMapper, Lombok  
- OpenAPI (Swagger UI)  
- Docker, Docker Compose  

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Girllovin/AccesssSystem.git
cd AccesssSystem
```

### 2. Run with Docker

```bash
docker-compose up --build
```

### 3. Run locally

```bash
./mvnw spring-boot:run
```

---

## Configuration

### Email (SMTP)

Set in `.env` or `application.properties`:

```
spring.mail.username=youremail@gmail.com  
spring.mail.password=your-apppassword
```

---

## Security

- Authentication: HTTP Basic Auth  
- Registration is publicly available  
- Role and password management requires authentication  
- Passwords are securely hashed  
- All endpoints are protected by Spring Security

---

## Project Structure

```plaintext
AccesssSystem/
├── accounting/            # User accounts
│   ├── controller/
│   ├── service/
│   ├── dao/
│   ├── dto/
│   └── model/
├── building/              # Buildings
├── client/                # Access card holders
├── door/                  # Doors
├── event/                 # Access events
├── configuration/         # Spring and Swagger configs
├── security/              # Security filters and settings
├── resources/
│   ├── application.properties
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── AccessControlApplication.java
```

---

## Module Overview

### `accounting/` — User Accounts  
Handles registration, login, password and role management.

### `building/` — Buildings  
Manages physical buildings and locations.

### `client/` — Cardholders  
CRUD operations for access card users.

### `door/` — Doors  
Door definitions and access control logic.

### `event/` — Events  
Access log and event tracking.

### Other  
Spring configs, security setup, Docker support, and build files.

---

## API Endpoints

- `POST /account/register` — Register a new user  
- `POST /account/login` — Authenticate (Basic Auth)  
- `PUT /account/password` — Change password  
- `GET /building` — Get list of buildings  
- `POST /building` — Add a building  
- `GET /door` — Get list of doors  
- `POST /door` — Add a door  
- `GET /cardholder` — Get cardholders  
- `POST /cardholder` — Add a cardholder  
- `GET /door/activity` — Get access events

---

## Authors

- **Sergey Gerlovin**  
- **Dmitry Shtrikman**  
- **Ekaterina Fedorenko**

This project showcases a full-stack access control system using Spring Boot and React.
