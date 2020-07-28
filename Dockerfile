FROM openjdk:14-alpine
COPY target/micronaut-data-jdbc-*.jar micronaut-data-jdbc.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "micronaut-data-jdbc.jar"]