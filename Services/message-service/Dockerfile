FROM maven:3.6.3-ibmjava-8-alpine AS msg-back

WORKDIR /usr/src/message-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /message-service
COPY --from=msg-back /usr/src/message-service/target/message-service.jar ./

CMD ["java", "-jar", "message-service.jar"]
