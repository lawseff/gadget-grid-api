# The PostgreSQL image
FROM postgres:16.2

# The defaults for easy `psql`. Only for local development!
ENV POSTGRES_DB root
ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD root

# Exposes the container's port for access from the host
EXPOSE 5432

# Creates the databases
COPY create_databases.sql /docker-entrypoint-initdb.d/