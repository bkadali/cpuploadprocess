############################################################
# Dockerfile to build spring boot app container images
# Based on Ubuntu
############################################################

FROM ubuntu:latest

MAINTAINER bhanu kadali

RUN apt-get update

RUN apt-get install default-jre -y

RUN apt-get install default-jdk -y

RUN apt-get install maven -y

ADD pom.xml /app/

ADD src/ /app/src/

WORKDIR /app/

RUN mvn package

ADD target/cpuploadprocess*.jar app.jar

RUN bash -c 'touch /app.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]