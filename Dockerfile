FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/EcommerceApplication-0.0.1-SNAPSHOT.jar /app/EcommerceApplication-0.0.1-SNAPSHOT.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "EcommerceApplication-0.0.1-SNAPSHOT.jar"]
