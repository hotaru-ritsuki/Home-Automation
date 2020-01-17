FROM java:8-jdk-alpine

COPY ./target/application-0.0.1.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch application-0.0.1.jar'

ENTRYPOINT ["java","-jar","application-0.0.1.jar"]