FROM eclipse-temurin:17-jdk-jammy
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

RUN mv build/libs/*.jar build/libs/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","build/libs/app.jar"]