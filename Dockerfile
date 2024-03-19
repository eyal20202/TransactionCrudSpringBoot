#define base docker image
FROM openjdk:17-jdk-oracle
LABEL maintainer="eyalmiz project"
ARG JAR_FILE=target/*.jar
COPY ./target/transaction-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java" ,"-jar","/app.jar"]