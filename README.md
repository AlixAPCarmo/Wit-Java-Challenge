# Challenge Calculator Project
This is a multi-module Java application designed to demonstrate modular programming, object-oriented principles, and inter-module communication using Apache Kafka (future implementation). The project includes basic arithmetic operations such as addition, subtraction, multiplication, and division.

# Project Overview

This project was generated using Spring Initializer and built with Spring Boot. The application follows a modular architecture, separating core business logic and RESTful API endpoints into distinct modules.
Modules

  - Calculator Module:
      Contains the core business logic for arithmetic operations.
      Implements object-oriented principles, such as abstraction and separation of concerns.

  - REST Module:
      Exposes RESTful APIs for arithmetic operations.
      Communicates with the calculator module for calculations.
      Serves as the entry point of the application.

# Technologies & Tools

- Java 23: Core language for the project.
- Spring Boot 3.4.1: Framework for building modular, enterprise-grade applications.
- Maven: Build automation tool for managing dependencies and modules.
- Apache Kafka: Used for inter-module communication (future implementation).
- Embedded Tomcat: Handles REST API requests.
- JUnit: For writing and running unit tests.

# Dependencies
Parent Module

- Spring Boot Starter Parent: Provides default dependency versions and configurations.
- Spring Kafka: Messaging integration (future use for Kafka communication).
- Spring Boot DevTools: Enables hot reloading during development.

Calculator Module

- Spring Boot Starter: Inherited from the parent module. No additional dependencies required.

REST Module
- Spring Boot Starter Web: Enables building RESTful APIs.
- Spring Kafka: Configured for Kafka producer/consumer communication.
- Calculator Module: Included as a dependency to access arithmetic logic.

# Known Issues
## Kafka Connection Issue

The application currently encounters issues with Kafka connectivity. Specifically:

  - Consumers and producers cannot establish connections to the Kafka broker.
- Topics, although listed in Kafka, are not recognized by the Spring application.

Steps Taken to Resolve

  - Verified Kafka broker status using kafka-broker-api-versions.sh.
    - Confirmed topics exist using kafka-topics.sh.
    - Tested connectivity between the containers (ping kafka was successful).
   
Because of this issue the project will return error 500. 

# File Structure

    Challenge/
    ├── calculator/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/
    │   │   │   │       └── witSoftware/
    │   │   │   │           └── calculator/
    │   │   │   │               ├── exception/        # Custom exceptions (e.g., DivisionByZeroException)
    │   │   │   │               ├── model/           # Data models (e.g., OperationRequest, OperationResult)
    │   │   │   │               ├── service/         # Business logic (e.g., CalculatorService)
    │   │   │   │               └── util/            # Utilities/helpers (e.g., InputValidator)
    │   │   │   └── resources/
    │   │   │       └── application.properties       # Module-specific configurations (if needed)
    │   │   └── test/
    │   │       ├── java/
    │   │       │   └── com/
    │   │       │       └── witSoftware/
    │   │       │           └── calculator/
    │   │       │               └── service/         # Unit tests for CalculatorService
    │   │       └── resources/
    │   └── pom.xml                                   # Calculator module-specific dependencies
    ├── rest/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/
    │   │   │   │       └── witSoftware/
    │   │   │   │           └── rest/
    │   │   │   │               ├── controller/      # REST API controllers (e.g., CalculatorController)
    │   │   │   │               ├── service/         # Business logic for REST module (if needed)
    │   │   │   │               └── exception/       # REST-specific exceptions
    │   │   │   └── resources/
    │   │   │       └── application.properties       # REST module-specific configurations
    │   │   └── test/
    │   │       ├── java/
    │   │       │   └── com/
    │   │       │       └── witSoftware/
    │   │       │           └── rest/
    │   │       │               └── controller/      # Unit tests for REST controllers
    │   │       └── resources/
    │   └── pom.xml                                   # REST module-specific dependencies
    ├── src/
    │   ├── main/
    │   │   └── java/
    │   │       └── com/
    │   │           └── witSoftware/
    │   │               └── ChallengeApplication.java # Temporary entry point for REST module
    ├── .gitignore                                    # Ignore unnecessary files like target/, .idea/, etc.
    ├── pom.xml                                       # Parent POM managing modules and shared dependencies
    └── README.md                                     # Documentation for the project

# How to Run the Project
## Step 1: Clone the Repository

git clone <repository-url>
cd Challange

## Step 2: Prerequisites

Ensure you have the following installed on your system:

- Java Development Kit (JDK 23):
Download and install from Oracle.

Verify installation:

    java -version

Maven:

    Install Maven from Apache Maven.
    Verify installation:

        mvn -version

## Step 3: Build the Project

Run the following command in the root directory (Challange):

mvn clean install

This will:

  - Compile the code.
  - Run tests.
  - Build the modules.

## Step 4: Run the Application

Navigate to the rest module directory in CLI:

    docker-compose build -d 

To turn it off run:

     docker-compose down -v

to run it again run:

    docker-compose up -d
## Step 5: Test the API

Use tools like Postman or cURL to test the REST API endpoints.
Example API call for addition:

    curl "http://localhost:8080/sum?a=5&b=10"

Expected response:

    {
      "result": 15
    }


# Future Enhancements

- Kafka Integration:
    Use and fix issues with Kafka for communication between rest and calculator modules.
- Docker Support:
    Improve Docker configuration for easy deployment.
- Improved Error Handling:
    Extend exception handling for invalid inputs and edge cases.
- Improve and implement logging and error reporting with SLF4J.

# Known Issues

Division by zero is currently handled using a custom exception (DivisionByZeroException), but the REST API returns a default error response.
