# Menstrual Cycle Tracking App

A Spring Boot application to track menstrual cycles, predict ovulation dates, and provide fertility insights.

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Technologies Used](#technologies-used)
5. [Installation](#installation)
6. [Usage](#usage)
7. [API Endpoints](#api-endpoints)
8. [Testing](#testing)
9. [Contributing](#contributing)
10. [License](#license)
11. [Contact](#contact)

## Introduction
The Menstrual Cycle Tracking App helps users track their menstrual cycles, predict ovulation, and identify fertility windows. This application is built with Spring Boot and includes user registration, login, and password management functionalities.

## Features
- User registration and authentication
- Tracking of menstrual cycle dates
- Prediction of next period and ovulation dates
- Identification of fertile and non-fertile days
- RESTful API endpoints for integration

## Architecture
The project follows a layered architecture with the following main components:
- **Model:** Contains entity classes like `User`.
- **Repository:** Interfaces extending `JpaRepository` for database operations.
- **Service:** Contains business logic and service implementations.
- **Controller:** REST controllers that handle HTTP requests.
- **Exceptions:** Custom exceptions and global exception handler.
- **DTO:** Data Transfer Objects for request and response payloads.
- **Utils:** Utility classes like `ApiResponse`.

## Technologies Used
- Spring Boot
- Spring Data JPA
- Lombok
- Hibernate
- JUnit for testing
- Maven for project management

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/menstrual-cycle-tracking-app.git
   cd menstrual-cycle-tracking-app

2. Install dependencies:
   mvn clean install

3. Run the application:
   mvn spring-boot:run

4. Access the application: The application will be running at http://localhost:8080.
   
### 8. Usage
Explain how to use the project, including any prerequisites and example requests.

```markdown
## Usage
### Prerequisites
- Java 17+
- Maven

### Example Requests

- **Register User:**
  ```bash
  POST /api/v1/users/register
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123"
  }

