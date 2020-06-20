FROM maven:3.6.3-ibmjava-8-alpine AS carr-back

WORKDIR /usr/src/car-request-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /car-request-service
COPY --from=carr-back /usr/src/car-request-service/target/car-request-service.jar ./

CMD ["java", "-jar", "car-request-service.jar"]
