FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=store-warsaw.jar
ADD ${JAR_FILE} store-warsaw.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/store-warsaw.jar"]