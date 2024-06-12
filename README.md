# Gadget Grid API

## Local development

### Services

The local services, such as Postgres and MinIO are configured via Docker Compose. 
Use the following commands inside the project root directory to interact with them:

1. To **initialize** the containers, use:
```sh
docker compose up -d
```

2. To **start the existing** containers, use:
```sh
docker compose start
```

3. To **stop** the containers, use:
```sh
docker compose stop
```

4. To **remove** the containers, use:
```sh
docker compose down
```

If you have Docker Compose v1, you can use `docker-compose` instead of `docker compose` for the 
commands above.

Also, Docker is required for running integration tests, as they use Testcontainers.

### Profile

Run the application with the `local` Spring profile to apply the local configuration.