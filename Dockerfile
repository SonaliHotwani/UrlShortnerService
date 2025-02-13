FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar long-url-shortener.jar
EXPOSE 8080
CMD java  -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar long-url-shortener.jar