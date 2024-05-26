FROM node:16-alpine as builder

WORKDIR /app

COPY package*.json ./

RUN npm i

COPY . .

RUN npm run build



FROM nginx:latest as production

COPY --from=builder /app/build ./usr/share/nginx/html

COPY --from=builder /app/nginx/conf.d /etc/nginx/conf.d

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]