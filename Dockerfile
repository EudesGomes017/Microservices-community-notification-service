# Etapa 1: build com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o projeto para dentro da imagem
COPY pom.xml .
COPY src ./src

# Compila e gera o .jar (com testes ignorados)
RUN mvn clean package -DskipTests

# Etapa 2: runtime com JDK leve
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta do serviço
EXPOSE 8083

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
