# Etapa 1: Compilar a aplicação
FROM gradle:8.7-jdk21-alpine AS build

WORKDIR /app

# Copia apenas os arquivos de configuração do Gradle primeiro (para cache eficiente)
COPY gradle ./gradle
COPY gradlew .
COPY build.gradle settings.gradle ./

# Baixa as dependências antes de copiar o código
RUN ./gradlew dependencies --no-daemon

# Agora copia o código-fonte
COPY src ./src

# Compila a aplicação sem rodar testes
RUN ./gradlew bootJar -x test --no-daemon

# Etapa 2: Criar a imagem final
FROM amazoncorretto:21.0.4-alpine

WORKDIR /opt/app

# Copia o JAR compilado da etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Define o comando de entrada corretamente
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
