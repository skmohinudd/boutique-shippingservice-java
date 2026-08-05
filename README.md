# boutique-shippingservice-java

Creates shipments and tracks delivery status for completed orders.

## Overview

- **Type:** Spring Boot service
- **Stack:** Java 21, Spring Boot, Maven, JPA, PostgreSQL, Flyway, Actuator, Docker

## Flow

```text
Client / service → Controller → Business logic → Database / events / downstream services
```

## Main APIs

```text
Get /id
```

## Database

```text
shipments
```

## Configuration

```text
DB_CONNECTION_TIMEOUT_MS
DB_MAX_LIFETIME_MS
DB_PASSWORD
DB_POOL_MAX_SIZE
DB_POOL_MIN_IDLE
DB_URL
DB_USERNAME
DB_VALIDATION_TIMEOUT_MS
```

## Run

```bash
./mvnw spring-boot:run
./mvnw clean verify
```

## Docker

```bash
docker build -t boutique-shippingservice-java:local .
```

## CI/CD

This repository is built and deployed independently through its own GitHub Actions workflow.
