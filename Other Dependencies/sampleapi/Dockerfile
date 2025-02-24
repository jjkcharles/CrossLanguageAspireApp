# Use an official Maven 3.6 image as the base
FROM maven:3.9-eclipse-temurin-21-alpine

# Set the working directory to /app
WORKDIR /app

RUN mkdir -p ./agents
RUN wget -P ./agents \
        https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

# Copy the Maven project files into the container
COPY pom.xml /app/

# Copy the application code into the container
COPY src /app/src/

# Build the Spring Boot application using Maven
RUN mvn clean package

# Expose the port that the Spring Boot application will use
EXPOSE 8080

# Run the command to start the Spring Boot application when the container launches
CMD ["java", "-javaagent:./agents/opentelemetry-javaagent.jar", "-jar", "target/sampleapi-0.0.1-SNAPSHOT.jar"]