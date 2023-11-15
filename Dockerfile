FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/DevOps_Project-2.1.jar /DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-2.1.jar"]