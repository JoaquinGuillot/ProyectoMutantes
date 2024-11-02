FROM amazoncorretto:17-alpine-jdk

COPY build/libs/mutantes-0.0.2-SNAPSHOT.jar app.jar

ENTRYPOINT ["java" , "-jar" , "/app.jar"]