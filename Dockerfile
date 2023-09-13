FROM maven:latest as build
FROM postgres
FROM openjdk:latest
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests
FROM adoptopenjdk:11-jre-hotspot
COPY --from=build /app/target/MiniBooking-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
