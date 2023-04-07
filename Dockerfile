FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ROOT.jar /app

CMD ["java", "-jar", "ROOT.jar"]
