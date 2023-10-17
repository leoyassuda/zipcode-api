include ./infra/.env
include ./infra/.env.pgdb.primary
include ./infra/.env.pgdb.replica

build-app-image:
	docker build --no-cache -f ./infra/Dockerfile --tag=$(DOCKER_APP_IMAGE_NAME):latest .

get-network-db:
	docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(DOCKER_DB_CONTAINER_NAME)

get-network-redis:
	docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(DOCKER_REDIS_CONTAINER_NAME)

install:
	./mvnw clean install

start-db:
	docker compose -f ./infra/docker-compose.yaml up postgres_primary postgres_replica pg_admin -d

start-infra:
	docker compose -f ./infra/docker-compose.yaml up postgres_primary postgres_replica redis pg_admin -d

start-app:
	docker compose -f ./infra/docker-compose.yaml up app -d

stop-app:
	docker compose -f ./infra/docker-compose.yaml down app

start:
	docker compose -f ./infra/docker-compose.yaml up -d

stop:
	docker compose -f ./infra/docker-compose.yaml down -v

rm-app:
	docker stop $(DOCKER_APP_IMAGE_NAME)
	docker rm $(DOCKER_APP_IMAGE_NAME)

all: install build-app-image start

restart: stop start
