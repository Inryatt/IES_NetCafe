FROM node:14.9
WORKDIR /usr/src/app
COPY ./webapp/package*.json ./
RUN npm install
COPY ./webapp .
EXPOSE 3000
CMD [ "npm", "start" ]