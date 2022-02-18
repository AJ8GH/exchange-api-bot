# Exchange API Bot

![example workflow](https://github.com/AJ8GH/exchange-api-bot/actions/workflows/push-to-main.yml/badge.svg)
[![codecov](https://codecov.io/gh/AJ8GH/exchange-api-bot/branch/main/graph/badge.svg?token=NFA2XZNGT6)](https://codecov.io/gh/AJ8GH/exchange-api-bot)
[![Maintainability](https://api.codeclimate.com/v1/badges/866c83ef3c114f9688f2/maintainability)](https://codeclimate.com/github/AJ8GH/exchange-api-bot/maintainability)

## Build info

- Java version: `17`
- Build tool: `Maven`

## Get started

Ensure Java 17 and maven are installed.

```shell
$ git clone git@github.com:AJ8GH/exchange-api-bot.git
$ cd exchange-api-bot
$ mvn clean install
```

Create file `launcher/src/main/resources/conf/api-secret.properties`

Add account properties:

```
esa.host=<value>
esa.port=<value>
esa.appKey=<value>
esa.username=<value>
esa.password=<value>
api.appKey=<value>
api.username=<value>
api.password=<value>
api.header.xIp=<value>
api.endpoint.betting=http://ang.internal/exchange/betting/rest/v1.0/
api.endpoint.login=http://identitysso.com.betfair/api/login
```
