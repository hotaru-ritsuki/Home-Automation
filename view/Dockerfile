FROM node:12.14.0 AS builder

RUN npm install
RUN $(npm bin)/ng build

FROM nginx

COPY --from=builder /app/dist/* /usr/share/nginx/html/

EXPOSE 4200

