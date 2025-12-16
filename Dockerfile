# Use official Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven config
COPY pom.xml .
RUN mkdir -p src && echo " " > src/temp.java
RUN mvn dependency:go-offline -B

# Copy all project files
COPY . .

# Build project
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/library-backend-0.0.1-SNAPSHOT.jar"]
