# ---------- Stage 1: Build the Spring Boot JAR ----------
FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the Spring Boot app ----------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Install netcat for waiting
RUN apt-get update && apt-get install -y netcat

# Copy the jar from the builder stage
COPY --from=builder /build/target/*.jar app.jar

# Copy wait script
COPY wait-for-mysql.sh .
RUN chmod +x wait-for-mysql.sh

ENTRYPOINT ["./wait-for-mysql.sh"]

