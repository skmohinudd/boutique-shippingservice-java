# Build the Spring Boot application with Java 21
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copy Maven files first so dependency downloads can be cached
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Download Maven dependencies before copying application source
RUN ./mvnw --batch-mode --no-transfer-progress dependency:go-offline

COPY src/ src/

# Build the executable JAR; tests are already executed in CI
RUN ./mvnw --batch-mode --no-transfer-progress clean package -DskipTests \
    && JAR_FILE="$(find target -maxdepth 1 -type f -name '*.jar' \
       ! -name 'original-*.jar' \
       ! -name '*-sources.jar' \
       ! -name '*-javadoc.jar' | head -1)" \
    && test -n "$JAR_FILE" \
    && cp "$JAR_FILE" /app/app.jar


# Use the smaller Java runtime image for the final container
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Run the application as a non-root user
RUN useradd --system --uid 10001 --no-create-home appuser

COPY --from=build --chown=10001:10001 /app/app.jar /app/app.jar

USER 10001

# Shipping Service HTTP port
EXPOSE 8087

ENTRYPOINT ["java","-Duser.timezone=UTC","-XX:MaxRAMPercentage=75.0","-jar","/app/app.jar"]