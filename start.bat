@echo off

:: Собираем проект через Maven
mvn clean install

:: Собираем и запускаем Docker-контейнеры
docker-compose build
docker-compose up -d