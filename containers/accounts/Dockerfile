FROM amazoncorretto:17.0.12-alpine

LABEL authors="chauri"

#Copy jar to image
COPY /target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the app
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]