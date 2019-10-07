FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=ecommercee.jar
ADD ${JAR_FILE} ecommercee.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ecommercee.jar"]