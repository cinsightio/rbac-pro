FROM node:alpine
WORKDIR /app
COPY package.json ./
COPY package-lock.json ./
COPY ./ ./
RUN npm i
ENV NODE_OPTIONS=openssl-legacy-provider
CMD ["npm",  "start"]