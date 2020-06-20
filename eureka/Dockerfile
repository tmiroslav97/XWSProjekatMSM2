FROM maven:3.6.3-ibmjava-8-alpine AS eureka-back

WORKDIR /usr/src/eureka
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /eureka
COPY --from=eureka-back /usr/src/eureka/target/eureka.jar ./

EXPOSE 8761

CMD ["java", "-jar", "eureka.jar"]
