FROM amazoncorretto:17-alpine-jdk

COPY out/artifacts/mutantes_jar/mutantes.jar app.jar

ENTRYPOINT ["java" , "-jar" , "/app.jar"]