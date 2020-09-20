FROM openjdk:8-jdk-alpine
MAINTAINER nemeth.samu.sandor@gmail.com

VOLUME /tmp

ADD target/*.jar products.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "products.jar"]