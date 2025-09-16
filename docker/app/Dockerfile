ARG IMG_JAVA

FROM ${IMG_JAVA} AS layers
WORKDIR /application
COPY *.jar app.jar

RUN adduser -S spring-user
USER spring-user

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
