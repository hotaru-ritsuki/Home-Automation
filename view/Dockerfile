FROM node:12.14.0-alpine AS builder

WORKDIR /app

COPY . .

RUN npm install && \
    npm run build

FROM nginx:alpine

EXPOSE 4200

COPY --from=builder /app/dist/* /usr/share/nginx/html/

CMD ng serve


