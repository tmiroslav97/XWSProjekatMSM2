FROM maven:3.6.3-ibmjava-8-alpine AS auth-back

WORKDIR /usr/src/authentication-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /authentication-service
COPY --from=auth-back /usr/src/authentication-service/target/authentication-service.jar ./

CMD ["java", "-jar", "authentication-service.jar"]
