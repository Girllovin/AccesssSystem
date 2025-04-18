# ---------- Сборочный этап ----------
FROM eclipse-temurin:17-jdk as build

WORKDIR /app

# Копируем wrapper и проект
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline

COPY . ./

RUN ./mvnw clean package -DskipTests

# ---------- Запусковой этап ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
