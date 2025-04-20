FROM openjdk:17
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "/app/target/minesweeper-1.0.jar"]