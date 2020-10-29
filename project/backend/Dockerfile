FROM openjdk:8-jre-alpine
VOLUME /tmp
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/menuGenerator.jar /app/menuGenerator.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/menuGenerator.jar"]

