FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

CMD ["java", "-cp", "target/notifications-lib-1.0.0.jar", "com.example.examples.NotificationExample"]
