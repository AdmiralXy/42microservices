FROM openjdk:8-jdk as builder
ADD src /app/src
ADD .mvn /app/.mvn
ADD mvnw /app
ADD pom.xml /app
WORKDIR /app
RUN ./mvnw package -DskipTests

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]