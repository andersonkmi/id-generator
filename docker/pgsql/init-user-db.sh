#!/usr/bin/env bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER idgenerator WITH PASSWORD 'idgenerator';
	CREATE DATABASE idgenerator;
	GRANT ALL PRIVILEGES ON DATABASE idgenerator TO idgenerator;
EOSQL

