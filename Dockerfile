# Build stage
FROM eclipse-temurin:17-jdk as build

# Set the working directory inside the container
WORKDIR /app

# Copy your Maven/Gradle build files first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Ensure mvnw is executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src src

# Package the app
RUN ./mvnw package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY --from=build /app/target/webdevtoons-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "app.jar"]