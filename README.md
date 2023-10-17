# Zipcode-API

Zipcode-API is a SpringBoot project to server a simple location based on zipcode.

The main idea is, show a Java project using clean architecture and some tools like a jdbc-template, cache using Redis,
SpringDoc, using tools like circuit breaker and cache.

## Tech Stack

**Built-in:**

* [Maven](https://maven.apache.org/)
* [Spring](https://spring.io/)
* [SpringDoc-OpenApi](https://springdoc.org/)
* [Docker](https://www.docker.com/)
* [Redis](https://redis.io/)
* [Postgres](https://www.postgresql.org/)
* [Spring-CircuitBreaker-Resilience4j](https://spring.io/projects/spring-cloud-circuitbreaker)

## Getting Started

### Pre-requirements

- Docker and Docker-Compose
- JDK 21
- Maven 3


```shell
git clone https://github.com/leoyassuda/zipcode-api
```

### Infra Setup

This project is based on Docker to start all dependencies before the application.

In `infra` folder exists all files to set up the development environment.

copy and edit values examples `env` files:
```shell
cp .env.example .env
cp .env.pgdb.primary.example .env.pgdb.primary
cp .env.pgdb.replica.example .env.pgdb.replica
cp .env.pgadmin.example .env.pgadmin
```

### Database client

Database client:

- pg_admin:
  - A powerful tool to admin Postgres instances. 
  - to login, see on the `env.pgadmin` file.
  - ![an image showing the login the page of pg-admin](img/pg-admin-login.png "pg admin login-page")

- Redis client:

- redisinsight:
  - A clean and great to access Redis instante. 
  - to login, can use localhost and port default.
  - ![an image showing form page of redisinsight](img/redis-insight-login.png "redisinsight login-form")

## Running

Installing dependencies and building application.

```shell
make install
```

build app image

```shell
make build-app-image  
```

Starting only infrastructure

```shell
make start-infra
```

or

To start everything
```shell
make start
```

See more commands in `Makefile`

## API Document

Access [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to see the API documentation.

## Utils

Some util commands

#### create image using maven

```shell
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=<docker_hub_user>/<repo>
```

#### push image

```shell
docker push <docker_hub_user>/zipcode-app
```

#### inspect using JQ

```shell
docker network inspect <network-name> | jq '.[0].IPAM.Config[0].Gateway'
```

#### export DB Ip Connection

```shell
export DB_CONNECTION_IP=$(docker network inspect <network-name> | jq -r '.[0].IPAM.Config[0].Gateway')
```

---

## Authors

- **Leo Yassuda** - _Initial work_ - [Portfolio](https://leoyas.com)
