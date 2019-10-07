FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=ecommerce.jar
ADD ${JAR_FILE} ecommerce.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ecommerce.jar"]