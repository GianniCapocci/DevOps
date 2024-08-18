FROM openjdk:21-rc-oracle AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src ./src
RUN ./mvnw  package -Dmaven.test.skip

FROM openjdk:17-jdk-alpine
WORKDIR /app
# Set non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
COPY --from=builder /app/target/*.jar /app/*.jar
COPY ./src/main/resources/static/uploads /app/src/main/resources/static/uploads
RUN chown -R appuser:appgroup /app/src/main/resources/static/uploads
RUN chmod -R 775 /app/src/main/resources/static/uploads
USER appuser

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app/*.jar"]