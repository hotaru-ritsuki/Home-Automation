


FROM java:8-jdk-alpine

COPY ./target/device-0.0.1.jar /usr/app/

WORKDIR /usr/app

EXPOSE 27017 8081

ENTRYPOINT ["java","-jar","device-0.0.1.jar"]