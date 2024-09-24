FROM openjdk:17-oracle

COPY target/*.jar demo.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]