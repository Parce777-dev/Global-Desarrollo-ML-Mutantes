FROM openjdk:17-jdk-slim
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test
EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/mutant-detector-0.0.1-SNAPSHOT.jar"]