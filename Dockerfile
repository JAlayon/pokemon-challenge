# Use a base image with JDK 21 (matching your Java version)
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the modules to the container
COPY pom.xml .
COPY common ./common
COPY service ./service

# Build the application
RUN mvn clean package -DskipTests

# Create a new image for the runtime
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/service/target/service-0.0.1-SNAPSHOT.jar /app/service.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/app/service.jar", "-Xmx1024m"]