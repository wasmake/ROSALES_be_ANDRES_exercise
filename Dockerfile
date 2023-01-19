FROM maven:3-jdk-11-slim AS BUILDER
WORKDIR /app
COPY pom.xml .
COPY spotless.xml .
RUN mvn -e -B dependency:resolve dependency:resolve-plugins
COPY src ./src
RUN mvn -e -B clean package

FROM openjdk:11-slim AS RUNNER
WORKDIR /app
COPY --from=BUILDER /app/target/roles-api-0.0.1-SNAPSHOT.jar roles.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "roles.jar"]
HEALTHCHECK --start-period=10s --interval=5s --timeout=3s \
    CMD curl -f http://localhost:8080/actuator/health || exit 1