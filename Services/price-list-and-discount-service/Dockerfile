FROM maven:3.6.3-ibmjava-8-alpine AS pad-back

WORKDIR /usr/src/price-list-and-discount-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /price-list-and-discount-service
COPY --from=pad-back /usr/src/price-list-and-discount-service/target/price-list-and-discount-service.jar ./

CMD ["java", "-jar", "price-list-and-discount-service.jar"]