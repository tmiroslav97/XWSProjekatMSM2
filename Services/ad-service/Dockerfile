FROM maven:3.6.3-ibmjava-8-alpine AS ad-back

WORKDIR /usr/src/ad-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /ad-service
COPY --from=ad-back /usr/src/ad-service/target/ad-service.jar ./

CMD ["java", "-jar", "ad-service.jar"]
