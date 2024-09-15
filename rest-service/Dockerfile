# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Install curl for downloading Gradle
RUN apt-get update && apt-get install -y curl

# Set the working directory in the container
WORKDIR /app

# Copy the entire project into the container
COPY . /app

# Make gradlew executable
RUN chmod +x ./gradlew

# Build the project
RUN ./gradlew clean bootJar

# Expose the port the service runs on
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/build/libs/rest-service-0.0.1-SNAPSHOT.jar"]