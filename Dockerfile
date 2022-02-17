FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/rbacpro-*.jar /home/server.jar
CMD ["java","-jar","/home/server.jar"]