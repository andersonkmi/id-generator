FROM postgres:17.5
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=password
#ENV POSTGRES_DB=idgenerator
#COPY init.sql /docker-entrypoint-initdb.d/
COPY init-user-db.sh /docker-entrypoint-initdb.d/
