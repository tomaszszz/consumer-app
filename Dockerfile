FROM openjdk:23-jdk-slim

RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/consumer-app-0.0.1-SNAPSHOT.jar consumer-app.jar
ENTRYPOINT ["java", "-jar", "consumer-app.jar"]