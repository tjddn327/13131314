FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=build/libs/weatherpick-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

COPY certificate.pfx /etc/ssl/certificate.pfx

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]