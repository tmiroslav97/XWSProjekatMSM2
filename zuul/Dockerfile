FROM maven:3.6.3-ibmjava-8-alpine AS zuul-back

WORKDIR /usr/src/zuul
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /zuul
COPY --from=zuul-back /usr/src/zuul/target/zuul.jar ./

CMD ["java", "-jar", "zuul.jar"]
