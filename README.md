<div align="center">
  <h1><strong>Book Store API</strong></h1>
  <p>
    A robust backend system for a bookstore application, built on the Spring Boot 3 platform.
  </p>
  <p>
    <img alt="Java" src="https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk"/>
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-3.5.4-green.svg?style=for-the-badge&logo=spring"/>
    <img alt="Maven" src="https://img.shields.io/badge/Maven-4.0.0-red.svg?style=for-the-badge&logo=apache-maven"/>
  </p>
</div>

---

## 🎯 Overview

**Book Store API** is a RESTful API designed to provide core functionalities for an online bookstore management system. The project adheres to software development best practices, including layered architecture, security, and scalability.

## ✨ Main Features

-   **Book & Category Management:**
    -   CRUD (Create, Read, Update, Delete) for books and categories.
    -   Advanced search and filtering for books (using JPA Specifications).
-   **User & Permission Management:**
    -   User registration and information management.
    -   Role-Based Access Control (RBAC) system.
    -   Flexible management of permissions and roles.
-   **Authentication & Security:**
    -   JWT (JSON Web Token) based authentication with OAuth2 Resource Server.
    -   Secure password encoding using `BCryptPasswordEncoder`.
    -   Handling of security exceptions.
-   **File Management:**
    -   Upload and manage book cover images.
    -   Integration with [Cloudinary](https://cloudinary.com/) cloud storage service.
-   **API Documentation:**
    -   Automatic API documentation generation with [SpringDoc OpenAPI](https://springdoc.org/) (Swagger UI).

## 🏗️ Architecture

The project is built on a Layered Architecture to ensure clarity, maintainability, and scalability:

-   **Controller Layer:** Handles HTTP requests, validates input (DTOs), and calls the corresponding services.
-   **Service Layer:** Contains the core business logic of the application.
-   **Repository Layer:** Interacts with the database via Spring Data JPA.
-   **Domain/Entity Layer:** Defines the business objects and maps them to the database.

## ⚙️ Technology Stack

| Area                   | Technology / Library                                                                                                                                                                     |
| ---------------------- |------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Core Framework**     | [Spring Boot](https://spring.io/projects/spring-boot)                                                                                                                                    |
| **Language**           | [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)                                                                                                  |
| **Data Access**        | [Spring Data JPA](https://spring.io/projects/spring-data-jpa)                                                                                                                            |
| **Security**           | [Spring Security](https://spring.io/projects/spring-security), [OAuth2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html), JWT |
| **Database**           | [MySQL](https://www.mysql.com/), [Redis](https://redis.io/) (for caching and token management)                                                                                            |
| **API Documentation**  | [SpringDoc OpenAPI (Swagger)](https://springdoc.org/)                                                                                                                                    |
| **Bean Mapping**       | [MapStruct](https://mapstruct.org/)                                                                                                                                                      |
| **File Storage**       | [Cloudinary](https://cloudinary.com/)                                                                                                                                                    |
| **Build & Dependencies** | [Apache Maven](https://maven.apache.org/)                                                                                                                                                |
| **Code Utilities**     | [Lombok](https://projectlombok.org/)                                                                                                                                                     |
| **Deployment**         | [Docker](https://www.docker.com/), [Docker Compose](https://docs.docker.com/compose/)                                                                                                    |
| **Code Quality**       | [Spotless Maven Plugin](https://github.com/diffplug/spotless/tree/main/plugin-maven)                                                                                                     |

## 🚀 Getting Started

### Prerequisites

-   [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or newer
-   [Apache Maven](https://maven.apache.org/download.cgi)
-   [Docker](https://www.docker.com/products/docker-desktop/) and [Docker Compose](https://docs.docker.com/compose/install/) (for the Docker option)

### Running the Application

You can run the application in one of the two ways described below.

---

#### **Option 1: Run with Docker Compose (Recommended)**

This is the simplest and fastest way to launch the entire system, including the application, database, and Redis.

1.  **Clone the repository**
    ```bash
    git clone https://github.com/quyen-tv/book-store-backend.git
    ```

2.  **Create a `.env` environment file**
    Create a file named `.env` in the project's root directory and fill in the necessary configuration details.
    
    ```dotenv
    # Database Connection Configuration (for Docker)
    SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/book_store
    SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=your_strong_password
    
    # Redis Connection Configuration (for Docker)
    SPRING_DATA_REDIS_HOST=redis
    SPRING_DATA_REDIS_PORT=6379
    
    # JWT Configuration
    JWT_SIGNER_KEY=your-super-secret-key-that-is-long-and-secure-for-jwt
    JWT_ACCESS_TTL_MINUTES=60
    JWT_REFRESHABLE_TTL_DAYS=7
    
    # Cloudinary Configuration
    CLOUDINARY_CLOUD_NAME=your_cloudinary_cloud_name
    CLOUDINARY_API_KEY=your_cloudinary_api_key
    CLOUDINARY_API_SECRET=your_cloudinary_api_secret
    ```

3.  **Launch the System**
    Run the following command from the project's root directory:
    ```bash
    docker-compose up -d --build
    ```
    This command will:
    - Build the Docker image for your application.
    - Launch containers for the application, MySQL, and Redis.

    The application will be accessible at `http://localhost:8080`.

---

#### **Option 2: Run Directly on Local Machine**

This method requires you to have MySQL and Redis installed and configured on your machine.

1.  **Clone the repository**
    ```bash
    git clone https://github.com/quyen-tv/book-store-backend.git
    ```

2.  **Configure Environment**
    -   Ensure you have installed and started MySQL and Redis servers on your machine.
    -   Open the `src/main/resources/application.yaml` file and update the configuration details to point to your local services.

    ```yaml
    spring:
      # Local MySQL Connection Configuration
      datasource:
        url: jdbc:mysql://localhost:3306/book_store_db # Change if necessary
        username: <your-local-mysql-username>
        password: <your-local-mysql-password>
      
      # Local Redis Connection Configuration
      redis:
        host: localhost
        port: 6379

    # JWT and Cloudinary Configuration (similar to above)
    jwt:
      signer-key: your-super-secret-key-that-is-long-and-secure-for-jwt
      # ...
    cloudinary:
      cloud-name: your_cloudinary_cloud_name
      # ...
    ```

3.  **Build and Run the Application**
    Use the Maven Wrapper to start:
    ```bash
    # For Windows
    ./mvnw.cmd spring-boot:run

    # For macOS/Linux
    ./mvnw spring-boot:run
    ```
    The application will start at `http://localhost:8080`.

## 📚 API Documentation

After the application has started, you can access the Swagger UI to view detailed API documentation and interact directly with the endpoints.

-   **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
-   **OpenAPI v3 Spec:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## ✅ Code Quality

The project uses the [Spotless Maven Plugin](https://github.com/diffplug/spotless/tree/main/plugin-maven) to automatically check and format the source code, ensuring consistency and readability.

To check and apply formatting:
```bash
./mvnw spotless:apply
```