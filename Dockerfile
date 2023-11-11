FROM openjdk:8-jdk-alpine
COPY target/DevOps_Project-2.1.jar DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-2.1.jar"]