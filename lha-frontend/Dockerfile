FROM node:21

WORKDIR /app

COPY . .

RUN yarn install --prefer-offline --frozen-lockfile --non-interactive

CMD ["yarn", "dev", "--host"]