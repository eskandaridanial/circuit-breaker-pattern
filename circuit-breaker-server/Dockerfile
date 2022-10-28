FROM openjdk:8
LABEL maintainer="doneskandari@gmail.com"
EXPOSE 8000
COPY ./target/circuit-breaker-server-1.0.0.jar server.jar
CMD ["java","-jar","server.jar"]