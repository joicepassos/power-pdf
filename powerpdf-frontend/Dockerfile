FROM node:18-alpine AS builder

WORKDIR /app

COPY package*.json package-lock*.json ./

COPY . .

RUN npm install

RUN npm run build

RUN ls -la

FROM node:18-alpine

WORKDIR /app

COPY --from=builder /app/. .

EXPOSE 3000

CMD ["npm", "start"]