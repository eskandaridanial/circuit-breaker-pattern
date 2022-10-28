FROM openjdk:8
LABEL maintainer="doneskandari@gmail.com"
EXPOSE 8001
COPY ./target/circuit-breaker-client-1.0.0.jar client.jar
CMD ["java","-jar","client.jar"]