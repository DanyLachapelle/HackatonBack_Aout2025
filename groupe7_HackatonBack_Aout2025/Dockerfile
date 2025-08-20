FROM openjdk:17-jdk-slim as build

RUN apt-get update && apt-get install -y \
    findutils \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk as final
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]