#FROM node:12.14.0 as builder
#
#RUN apt update
#
#RUN apt install -y git nginx
#
#RUN mkdir /app
#
#WORKDIR /app
#
#COPY . .
#
#RUN npm i
#
#RUN npm run build
#
#RUN rm -rf /var/www/html
#
#RUN mv ./dist/view /var/www/html
#
#RUN mv ./default.conf /etc/nginx/conf.d/default.conf
#
#RUN rm -rf /app
#
#
##CMD ["nginx", "-g", "daemon off;"]
#CMD /bin/bash -c "'\$PORT'" && nginx -g 'daemon off;'
##CMD sed -i -e 's/$PORT/'"$PORT"'/g' /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'

FROM node:12.14.0-alpine as node

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

# Stage 2
FROM nginx:1.13.12-alpine

RUN rm -rf /usr/share/nginx/html/*
COPY ./default.conf /etc/nginx/conf.d/default.conf

COPY --from=node /app/dist /usr/share/nginx/html

CMD sed -i -e 's/$PORT/'"$PORT"'/g' /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'

