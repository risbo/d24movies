# README.md

## Project Setup and Requirements

This project is a Spring Boot application that uses Maven for dependency management and build automation. To run this project, you will need the following:

### Requirements
- **Java 17**: Ensure you have Java 17 installed. You can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use any preferred distribution.
- **Maven**: Make sure Maven is installed. You can download it from the [official Maven website](https://maven.apache.org/download.cgi).
- **Git**: You will need Git to clone the repository. You can download it from the [official Git website](https://git-scm.com/downloads).

### Cloning the Repository
To get started, you need to clone the repository. Open your terminal or command prompt and run the following command:

```bash
git clone https://github.com/risbo/d24movies.git

cd d24movies

mvn spring-boot:run
```
### Running the Tests
To run the tests for the application, use the following Maven command:

```bash
mvn test
```

### Swagger UI
You can access the Swagger UI for API documentation at the following URL:
```bash
http://localhost:8080/swagger-ui/index.html
```
This will provide you with an interactive interface to explore and test the API endpoints.

### API Endpoint

The API is exposed at http://localhost:8080/api/directors?threshold=4. This endpoint returns a list of directors whose movies exceed the specified threshold.

### Summary
- Ensure Java 17, Maven, and Git are installed.
- Clone the repository using Git.
- Navigate to the project directory and run the application using Maven.
- Access the API at http://localhost:8080/api/directors?threshold=4.
- Explore the API documentation at http://localhost:8080/swagger-ui/index.html.
