# ---------- СБОРКА ----------
FROM eclipse-temurin:21-jdk-alpine as build

# Установка зависимостей
RUN apk update && \
    apk upgrade && \
    apk add --no-cache tzdata curl unzip bash git && \
    rm -rf /var/cache/apk/*

# Установка Maven вручную
ENV MAVEN_VERSION=3.9.6
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar -xz -C /opt/ && \
    ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

WORKDIR /app

# Кэш зависимостей
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Копируем остальной код и собираем JAR
COPY . ./
RUN ./mvnw clean package -DskipTests

# ---------- ЗАПУСК ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
