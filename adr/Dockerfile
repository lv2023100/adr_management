FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY /target/*.war app.war
ENTRYPOINT ["java","-jar","-XX:+UseZGC","-XX:+ZProactive","--add-opens=java.base/java.util=ALL-UNNAMED","/app.war"]