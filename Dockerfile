FROM openjdk:17-oracle
WORKDIR /spring
COPY target/demo-0.0.1-SNAPSHOT.jar /spring/demo-0.0.1-SNAPSHOT.jar
EXPOSE 5432
EXPOSE 8080
ENTRYPOINT ["java","-jar","/spring/demo-0.0.1-SNAPSHOT.jar"]