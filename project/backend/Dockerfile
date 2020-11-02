FROM openjdk:latest
WORKDIR /app
COPY target/menuGenerator.jar /app/menuGenerator.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/menuGenerator.jar"]

