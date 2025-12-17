FROM postgres:16-alpine

# Copy initialization scripts
COPY schema.sql /docker-entrypoint-initdb.d/01-schema.sql
COPY init.sql /docker-entrypoint-initdb.d/02-init.sql

# Expose PostgreSQL port
EXPOSE 5432