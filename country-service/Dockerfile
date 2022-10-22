FROM openjdk:8-jdk as builder
WORKDIR /app
ADD src src
ADD .mvn .mvn
ADD mvnw .
ADD pom.xml .
RUN sed -i 's/\r//' mvnw
RUN ./mvnw package -DskipTests

FROM openjdk:8-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]