FROM openjdk:11-jdk

WORKDIR /app

COPY target/DevOps_Project-2.1.jar /app/DevOps_Project-2.1.jar

EXPOSE 8082

CMD ["java", "-jar", "DevOps_Project-2.1.jar"]