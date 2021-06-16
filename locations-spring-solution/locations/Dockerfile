FROM adoptopenjdk:16-jre-hotspot
RUN mkdir /opt/app
COPY target/locations-0.0.1-SNAPSHOT.jar /opt/app/locations.jar
CMD ["java", "-jar", "/opt/app/locations.jar"]