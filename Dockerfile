# ----------------------------------------------------------------
# - DOCKERFILE
# - AUTOR: Wilman Ortiz
# - FECHA: 31-Marzo-2021
# - DESCRIPCION: Dockerfile que permite la creacion del
# -              contenedor con el servicio de comunicaciones de
# -              alinaza rebelde
# ----------------------------------------------------------------

# escape=\ (backslash)
# Imagen base del Docker Registry para compilar nuestra servicio de comunicaciones
# Build Stage
FROM maven:3.6.3-ibmjava-8-alpine AS builder
WORKDIR /build/
COPY pom.xml .
COPY ./src ./src
RUN mvn clean package -Dmaven.test.skip=true

# Run Stage
FROM openjdk:8-jre-alpine

 # Parametrizacion del nombre del archivo que genera spring boot
ARG JAR_FILE=comunications-service.jar
ARG BUILD_DATE
ARG BUILD_VERSION
ARG BUILD_REVISION

ENV APP_HOME="/app" \
    JAVA_OPTS="" \
    HTTP_PORT=8080 \
    MONITO_PORT=9090

# Creando directorios de la aplicacion y de carga temporal de los archivos
RUN mkdir $APP_HOME

# Informacion de la persona que mantiene la imagen
LABEL org.opencontainers.image.created=$BUILD_DATE \
      org.opencontainers.image.authors="Wilman Ortiz Navarro" \
      org.opencontainers.image.url="https://github.com/dev-io21/ml-challenge/blob/master/Dockerfile" \
      org.opencontainers.image.documentation="https://i.pinimg.com/originals/d4/6d/23/d46d2360568e9222d59663a408d865fe.jpg" \
      org.opencontainers.image.source="https://github.com/dev-io21/ml-challenge/blob/master/Dockerfile" \
      org.opencontainers.image.version=$BUILD_VERSION \
      org.opencontainers.image.revision=$BUILD_REVISION \
      org.opencontainers.image.vendor="Alianza Rebelde | https://developer.io/" \
      org.opencontainers.image.licenses="http://www.apache.org/licenses/LICENSE-2.0.html" \
      org.opencontainers.image.title="Servicio de gestion de comunicaciones de la alianza rebelde" \
      org.opencontainers.image.description="El siguiente servicio permite garantizar la transferencia de mensajes seguros entre las naves que conforman la alianza rebelde para asi evitar interceptaciones no autorizadas del imperio"

# Puerto de exposicion del servicio
EXPOSE $HTTP_PORT
EXPOSE $MONITOR_PORT

# Copiando el compilado desde builder
COPY --from=builder /build/target/$JAR_FILE $APP_HOME/

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar ${APP_HOME}/comunications-service.jar"]