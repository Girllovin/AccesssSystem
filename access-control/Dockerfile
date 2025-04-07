# base image with Java 21 and Alpine Linux
FROM eclipse-temurin:21-jdk-alpine

# install utilities: time zone data, curl, unzip, bash, git
RUN apk update && \
    apk upgrade && \
    apk add --no-cache tzdata curl unzip bash git && \
    rm -rf /var/cache/apk/*

# Maven 
ENV MAVEN_VERSION=3.9.6
RUN curl -fsSL https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz | tar -xz -C /opt/ && \
    ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn


# working directory in the container
WORKDIR /opt/app

# caching dependencies
COPY pom.xml mvnw /opt/app/
COPY .mvn /opt/app/.mvn

# offline mode for dependencies
RUN ./mvnw dependency:go-offline

# project into the container
COPY . /opt/app

# default command: run 
CMD ["./mvnw", "spring-boot:run"]