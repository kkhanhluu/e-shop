FROM postgres:15.1

COPY ./init-multiple-database.sh /docker-entrypoint-initdb.d/