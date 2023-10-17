FROM eclipse-temurin:21-jdk-alpine
RUN mkdir /opt/app
COPY target/zipcode-1.0.0-SNAPSHOT.jar /opt/app/japp.jar
CMD ["java", "-jar", "/opt/app/japp.jar"]