# Start with maven:3.9.1-eclipse
FROM maven:3.9.1-eclipse-temurin-17

LABEL maintainer="Leonardo Dominici <leonardo_requena@outlook.com>"
# Set the working directory to /app
WORKDIR /app

# Copy the source code to the container
COPY . .

# Build the application with Maven
RUN mvn package

# Expose default Spring Boot port
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/fiapProject-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=test"]

#End