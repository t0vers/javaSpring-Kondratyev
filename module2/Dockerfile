FROM maven:3.8.7-openjdk-18-slim as builder
COPY . .
RUN mvn --no-transfer-progress clean package -Dmaven.test.skip
FROM --from=builder eclipse-temurin:21-jre-jammy
COPY target/module2-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]