FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update &&\
   apt-get install -y curl
COPY target/encourage-0.0.10-SNAPSHOT.jar encourage-0.0.10.jar
COPY src/main/resources/data.sql /app/src/main/resources/
ENTRYPOINT ["java", "-jar", "/app/encourage-0.0.10.jar"]
EXPOSE 8080