# Base image: Java 21 JDK + Alpine
FROM eclipse-temurin:21-jdk-alpine

# Установка утилит и зависимостей
RUN apk update && \
    apk upgrade && \
    apk add --no-cache tzdata curl unzip bash git && \
    rm -rf /var/cache/apk/*

# Установка Maven вручную
ENV MAVEN_VERSION=3.9.6
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar -xz -C /opt/ && \
    ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn

# Рабочая директория
WORKDIR /opt/app

# Копируем файлы Maven и делаем wrapper исполняемым
COPY mvnw pom.xml /opt/app/
COPY .mvn /opt/app/.mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Копируем оставшуюся часть проекта
COPY . /opt/app

# Команда по умолчанию (можно заменить на jar run при необходимости)
CMD ["./mvnw", "spring-boot:run"]
