# Practice Portal - University Internship Management System

## Project Overview

This repository contains the source code and files for the **Practice Portal**, a full-stack Spring Boot system designed to manage the University's mandatory student internship program. The application implements **Role-Based Access Control (RBAC)** and advanced **Strategy Pattern** for dynamic student/Company/professor matching. The screenshots below provide a quick visual tour of the key functional areas.

---

## How to Run

### Requirements

- Java 21 (Oracle JDK or OpenJDK)
- Apache Maven 3.9+
- MySQL Server (if using the MySQL profile)

## Linux

### (Optional) Reset Database, Build and Run

```bash
# (Optional) Reset database (Flyway clean + migrate)
./database_clean.sh

# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

## Windows

### (Optional) Reset Database, Build and Run

```powershell
# (Optional) Reset database (Flyway clean + migrate)
.\database_clean_windows.ps1

# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

### Aplication URL: http://localhost:8080/

---
## Development Data (Manual HTTP Requests)
Sample HTTP request files are available under: src/main/resources/dev-data
These `.http` files contain ready-to-use POST requests that can be executed using any HTTP client (e.g., Postman, curl, or similar tools).

They are intended for development and testing purposes, allowing you to manually populate the database with sample data and verify that the application and API endpoints are functioning as expected.


---

## Visual Walkthrough (Screenshots)

### ******************
![Login Page and Role Selection](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/1.png?raw=true)

### ******************
![Practice Office Matching Interface](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/2.png?raw=true)

### ******************
![Student Dashboard View](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/3.png?raw=true)

### ******************
![Company Dashboard - Posted Positions](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/4.png?raw=true)

### ******************
![Combined Evaluation Review (Admin View)](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/5.png?raw=true)

### ******************
![Company Evaluation Form](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/6.png?raw=true)

### ******************
![Professor Dashboard - Assigned Students](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/7.png?raw=true)

### ******************
![Professor Evaluation Form](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/8.png?raw=true)

### ******************
![Student Registration Form](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/9.png?raw=true)

### ******************
![Company Position Posting Form](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/10.png?raw=true)

### ******************
![Practice Office - User Management](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/11.png?raw=true)

### ******************
![Example of Traineeship Assignment Details](https://github.com/xrddev/practice-portal/blob/main/browser_screenshots/12.png?raw=true)