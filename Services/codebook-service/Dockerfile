FROM maven:3.6.3-ibmjava-8-alpine AS codebook-back

WORKDIR /usr/src/codebook-service
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /codebook-service
COPY --from=codebook-back /usr/src/codebook-service/target/codebook-service.jar ./

CMD ["java", "-jar", "codebook-service.jar"]