# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy tessdata and set the environment variable
COPY ["tessdata_best", "/app/tessdata_best"]
ENV TESSDATA_PREFIX=/app/tessdata_best

# Install required tools
RUN apt-get update && \
    apt-get install -y maven tesseract-ocr

# Copy application files and build the project
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Install tesseract-ocr in the runtime container
RUN apt-get update && \
    apt-get install -y tesseract-ocr && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the tessdata directory from the build stage
COPY --from=build /app/tessdata_best /app/tessdata_best
ENV TESSDATA_PREFIX=/app/tessdata_best

# Copy the application JAR file
COPY --from=build /app/target/ocrservice-0.0.1-SNAPSHOT.jar ./app.jar

# Expose application port
EXPOSE 8082

# Command to run the application
CMD ["java", "-jar", "app.jar"]
