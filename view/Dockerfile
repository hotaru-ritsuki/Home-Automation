FROM node:12.14.0 as builder

RUN apt update

RUN apt install -y git nginx

RUN mkdir /app

WORKDIR /app

COPY . .

RUN npm i

RUN npm run build

RUN rm -rf /var/www/html

RUN mv ./dist/view /var/www/html

RUN mv ./default.conf /etc/nginx/conf.d/default.conf

RUN rm -rf /app


RUN service nginx restart

#CMD ["nginx", "-g", "daemon off;"]
CMD /bin/bash -c "envsubst '\$PORT' < /etc/nginx/conf.d/default.conf > /etc/nginx/conf.d/default.conf" && nginx -g 'daemon off;'


