FROM eclipse-temurin:17-jdk-alpine

COPY ./build/libs/lha-bff-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
EXPOSE 8443

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar $ARGS