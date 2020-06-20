FROM node:alpine3.11 AS front

WORKDIR /usr/src/agentApp 
COPY agent-app-front .
RUN ["npm", "install"]
RUN [ "npm", "run", "build"]

FROM maven:3.6.3-ibmjava-8-alpine AS back

WORKDIR /usr/src/agentApp
COPY agent-app .
COPY --from=front /usr/src/agentApp/build ./src/main/resources/static
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
WORKDIR /agentApp
COPY --from=back /usr/src/agentApp/target/agentApp.jar ./

EXPOSE 8083

CMD ["java", "-jar", "agentApp.jar"]