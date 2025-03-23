# Dockerfile na raiz do projeto
FROM docker:buildenv AS builder

WORKDIR /app

# Copiar arquivos necessários para build
COPY . .

# Construir frontend
#WORKDIR /app/frontend
#RUN npm install && npm run build

# Construir backend
WORKDIR /app/backend
COPY ./backend/Dockerfile .
RUN ./gradlew build

# Criar imagem final
FROM docker:runtime

# Configurar ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE=prod
ENV RABBITMQ_URL=amqp://rabbitmq:5672
ENV DATABASE_URL=mysql://root:password@mysql:3306/pdf_processing

# Copiar arquivos compilados
#COPY --from=builder /app/frontend/dist ./frontend
COPY --from=builder /app/backend/build/libs/*.jar ./backend/app.jar

# Expor portas
EXPOSE 8080 8081 5672 3306
