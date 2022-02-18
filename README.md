# Exchange API Bot

![example workflow](https://github.com/AJ8GH/exchange-api-bot/actions/workflows/push-to-main.yml/badge.svg)

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
