# Stage 1: Build
FROM maven:3.9.4-eclipse-temurin-21 as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Package
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


# Dockerfile
#FROM openjdk:21
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
