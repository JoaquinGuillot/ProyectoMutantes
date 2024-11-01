FROM amazoncorretto:17-alpine-jdk

COPY build/libs/mutantes-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java" , "-jar" , "/app.jar"]