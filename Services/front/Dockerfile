FROM node:alpine3.11 AS front

WORKDIR /usr/src/front 
COPY . .
RUN ["npm", "install"]
RUN ["npm", "audit", "fix"]
RUN ["npm", "run", "build"]

FROM mhart/alpine-node

RUN yarn global add serve
WORKDIR /app
COPY --from=front /usr/src/front/build  .
CMD ["serve", "-p", "80", "-s", "."]
