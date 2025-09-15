FROM nexus.csir.reb:8181/bellsoft/liberica-openjre-alpine:21-cds AS layers
WORKDIR /application
COPY docker/*.jar app.jar

RUN adduser -S spring-user
USER spring-user

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
