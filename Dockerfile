FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn package

FROM eclipse-temurin:21.0.1_12-jre-jammy
RUN mkdir /opt/app
COPY --from=build /app/target/MovieWebsite-0.0.1-SNAPSHOT.jar /opt/app/movie-app.jar
ENTRYPOINT java -jar /opt/app/movie-app.jar
EXPOSE 8080