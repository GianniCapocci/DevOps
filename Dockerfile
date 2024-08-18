# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar /app/*.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "*.jar"]
