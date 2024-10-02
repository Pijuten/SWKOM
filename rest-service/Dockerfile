# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apt-get update && \
    apt-get install -y maven
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/openapi-spring-1.0.11.jar ./app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]