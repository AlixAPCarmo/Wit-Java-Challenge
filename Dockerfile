# Stage 1: Build all modules
FROM maven:3.9.9-eclipse-temurin-23 AS builder
WORKDIR /app

# Copy the entire project, including all modules
COPY . .

# Build all modules in one Maven reactor
RUN mvn clean package -DskipTests

# Stage 2: Runtime environment for REST
FROM openjdk:23-jdk
WORKDIR /app

# Copy only the REST JAR
COPY --from=builder /app/rest/target/rest-0.0.1-SNAPSHOT.jar app.jar

# Expose REST API port
EXPOSE 8080

# Run the REST service
ENTRYPOINT ["java", "-jar", "app.jar"]
