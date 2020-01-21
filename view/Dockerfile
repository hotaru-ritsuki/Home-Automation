FROM node:12.14.0 as builder

RUN apk update && apk add --no-cache make git

WORKDIR /app

COPY package.json package-lock.json  /app/

RUN cd /app && npm set progress=false && npm install

COPY .  /app

RUN cd /app && npm run build

FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 4200

CMD ["nginx", "-g", "daemon off;"]
