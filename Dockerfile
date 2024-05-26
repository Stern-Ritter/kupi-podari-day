FROM gradle:7.6.0-jdk17 AS build

WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .

COPY src ./src

RUN gradle build --no-daemon

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 3000

CMD ["java", "-jar", "app.jar"]