# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target directory to /app directory in the container
COPY target/dev_ops-0.0.1-SNAPSHOT.jar /app/dev_ops-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 9090

# Run the JAR file
ENTRYPOINT ["java", "-jar", "dev_ops-0.0.1-SNAPSHOT.jar"]
