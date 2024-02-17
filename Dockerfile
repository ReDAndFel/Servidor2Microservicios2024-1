FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el archivo JAR al contenedor
COPY build/libs/Taller1Servermic-1.0-SNAPSHOT.jar app.jar

# Exponer el puerto donde escucha tu aplicación
EXPOSE 8080

# Iniciar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
