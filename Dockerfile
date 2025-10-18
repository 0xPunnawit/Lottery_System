# ----- Build stage -----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests clean package

# ----- Runtime stage -----
FROM eclipse-temurin:17-jre
RUN useradd -ms /bin/bash spring
USER spring
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8888
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
