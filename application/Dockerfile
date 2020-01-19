FROM maven:latest as builder

COPY pom.xml /usr/local/pom.xml
COPY server /usr/local/server
COPY client /usr/local/client
WORKDIR /usr/local

RUN mvn clean instal


FROM java:8-jdk-alpine
RUN git clone https://github.com/hotaru-ritsuki/Home-Automation/pulls
ARG db_url
EXPOSE 3036 8080
COPY ./target/application-0.0.1.jar application-0.0.1.jar
ENTRYPOINT ["java","-jar","application-0.0.1.jar"]