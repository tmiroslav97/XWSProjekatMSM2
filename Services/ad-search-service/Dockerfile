FROM maven:3.6.3-ibmjava-8-alpine AS search-back

WORKDIR /usr/src/ad-search-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /ad-search-service
COPY --from=search-back /usr/src/ad-search-service/target/ad-search-service.jar ./

CMD ["java", "-jar", "ad-search-service.jar"]