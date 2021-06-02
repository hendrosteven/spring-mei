FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
COPY target/demo-app-0.0.1-SNAPSHOT.jar demoapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demoapp.jar"]
