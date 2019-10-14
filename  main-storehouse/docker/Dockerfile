FROM openjdk:8-jdk-alpine
EXPOSE 8081
ARG JAR_FILE=main-storehouse.jar
ADD ${JAR_FILE} main-storehouse.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/main-storehouse.jar"]