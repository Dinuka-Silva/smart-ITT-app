# 🚛 SMART ITT - Inter Terminal Transportation Management System

<div align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/React_Native-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" alt="React Native" />
  <img src="https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white" alt="TypeScript" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
</div>

<br />

**SMART ITT** is a comprehensive, full-stack logistics and transportation management solution designed to streamline container movement, driver assignments, and terminal reporting. With a powerful mobile application for personnel and a robust backend API, SMART ITT ensures seamless coordination across terminal operations.

## ✨ Key Features

### 👨‍✈️ Driver Hub
- **Trip Management**: Receive, accept, and update the status of assigned container transportation trips.
- **Real-Time Tracking**: Update trip status (Pending, In Progress, Completed) effortlessly from the mobile app.
- **Secure Authentication**: PIN and JWT-based secure login flow for operators.

### 📋 Supervisor Dashboard
- **Analytics & Reporting**: Daily snapshot of completed, pending, and rejected trips across the terminal.
- **Resource Management**: Assign containers and vehicles dynamically based on priority.
- **Driver Overview**: Monitor driver performance, active assignments, and statuses.

## 🛠 Tech Stack

### Frontend (Mobile App)
- **Framework**: React Native with [Expo](https://expo.dev/)
- **Language**: TypeScript
- **State Management**: Zustand
- **Data Fetching**: React Query & Axios
- **Routing**: Expo Router

### Backend (REST API)
- **Framework**: Spring Boot (Java)
- **Database**: PostgreSQL (with Flyway for migrations)
- **Security**: Spring Security & JWT authentication
- **Documentation**: OpenAPI / Swagger

---

## 🚀 Getting Started

### Prerequisites
Make sure you have the following installed on your machine:
- [Docker & Docker Compose](https://www.docker.com/products/docker-desktop/)
- [Node.js](https://nodejs.org/en/) (v18+)
- Java Development Kit (JDK 17+)

### 1️⃣ Start the Backend & Database
The backend and PostgreSQL database are fully containerized for easy setup.
```bash
# From the root directory, start the containers in detached mode
docker-compose up -d
```
*The backend API will be available at `http://localhost:8080`*

### 2️⃣ Start the Frontend App
Navigate into the frontend directory to run the mobile application.
```bash
cd frontend

# Install dependencies
npm install

# Start the Expo development server
npm run start
```
*You can run the app on iOS/Android simulators, on the web, or on your physical device using the Expo Go app.*

---

## 📂 Project Structure

```bash
smart_itt_app_v2/
├── backend/                  # Spring Boot Java Application
│   ├── src/main/java/...     # Controllers, Services, Repositories, Security
│   ├── src/main/resources/   # App Config & Flyway SQL Migrations
│   └── pom.xml               # Maven Dependencies
├── frontend/                 # React Native / Expo Application
│   ├── app/                  # File-based routing (Supervisor & Driver tabs)
│   ├── src/components/       # Reusable UI components
│   ├── src/services/         # API integration layers
│   └── src/store/            # Zustand global state
├── docker-compose.yml        # Multi-container orchestration
└── README.md
```

## 🔐 Authentication
The system uses stateless JSON Web Tokens (JWT) for secure authentication. 
- During development, demo accounts are automatically seeded into the database via Flyway migrations (`V3__Seed_Demo_Accounts.sql`).
- Login with the provided supervisor or driver credentials to access role-specific UI flows in the frontend.

---
<div align="center">
  <i>Built for the future of Inter-Terminal Transportation.</i>
</div>
