FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar
WORKDIR /usr/src/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
