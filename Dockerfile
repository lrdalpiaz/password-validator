FROM amazoncorretto:11
MAINTAINER lrdalpiaz@gmail.com
COPY build/libs/password-validator-0.0.1-SNAPSHOT.jar /opt/app/
CMD ["java", "-jar" , "/opt/app/password-validator-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
