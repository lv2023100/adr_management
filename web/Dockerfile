FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY /target/*.war app.war
ENTRYPOINT ["java","-jar","-XX:+UseZGC","-XX:+ZProactive","/app.war"]