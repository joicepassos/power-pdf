# Dockerfile raiz
FROM docker:buildenv AS builder
WORKDIR /app
# Copiar arquivos necessários para build
COPY . .
# Construir backend
WORKDIR /app/backend/files-service
COPY ./backend/files-service/Dockerfile .
RUN ./gradlew build

WORKDIR /app/backend/file-processor-service
COPY ./backend/file-processor-service/Dockerfile .
RUN ./gradlew build

WORKDIR /app/backend/storage-service
COPY ./backend/storage-service/Dockerfile .
RUN ./gradlew build
# Construir frontend
WORKDIR /app/frontend
COPY ./frontend/Dockerfile .
RUN npm install && npm run build

# Criar imagem final
FROM amazoncorretto:21.0.4-alpine

# Instalar MinIO Client (mc)
RUN apk --no-cache add curl bash && \
    curl -O https://dl.min.io/client/mc/release/linux-amd64/mc && \
    chmod +x mc && \
    mv mc /usr/local/bin/
ENV MINIO_ACCESS_KEY=minio
ENV MINIO_SECRET_KEY=minio123
ENV MINIO_HOST=http://localhost:9000

# Copiar arquivos compilados
COPY --from=builder /app/backend/build/libs/*.jar ./backend/files-service/app.jar
COPY --from=builder /app/backend/build/libs/*.jar ./backend/file-processor-service/app.jar
COPY --from=builder /app/backend/build/libs/*.jar ./backend/storage-service/app.jar

# Configuração de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE=prod
ENV RABBITMQ_URL=amqp://rabbitmq:5672
ENV DATABASE_URL=mysql://root:password@mysql:3306/powerfiles

# Expor portas
EXPOSE 8080 8081 8082 5672 3306 3000

# Configurar o MinIO Client e tornar o bucket público
CMD mc alias set myminio ${MINIO_HOST} ${MINIO_ACCESS_KEY} ${MINIO_SECRET_KEY} && \
    mc policy set public myminio/files-bucket && \
    java ${JAVA_OPTS} -jar /backend/files-service/app.jar