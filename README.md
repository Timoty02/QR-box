Основной функционал приложения - загрузка фотографии содержимого коробки и названии и получении в ответ qr-кода для дальнешей распечатки и наклейки на коробку, чтобы потом можно было просто отсканировать qr-код и увидеть что внутри, без необходимости открывать коробку. Дело в том что коробки зачастую сложены друг на друге и чтобы проверить, например, самую нижнюю из них необходимо будет снять все коробки что лежат на ней. С помощью qr-кода можно будет быстро посмотреть что лежит к каждой коробке и найти нужную.

# Стек технологий:
* Java 21
* Spring Boot
* Hibernate
* Thymeleaf
* PostgreSQL
* Docker
* Maven

# API проекта:
Проект доступен по адресу `http://localhost:8080/` (или ином, если указаны другие параметры в `application.properties`, за порт отвечает параметр `server.port`, за хост `server.host`)
Пользователь начинает работу с приложением с главной страницы, введя название создаваемой коробки(опционально) и загружая картинку через форму, а затем нажимая на кнопку `загрузить`, пользователь попадает на эндпоинт `POST /box/upload` , если данные валидны, будет отображена страница с id созданной коробки, qr-кодом, а также кнопка для прямого перехода.
При нажатии кнопки или сканирования qr-кода, пользователь попадает на эндпоинт `GET /box/{id}/image` где увидит название коробки и картинку.
Также на главной странице есть поле для поиска коробки по названию: введя название, и нажав `найти` пользователь попадёт на эндпоинт `GET /box/find/qr`, который по сути повторяет эндпоинт `/box/upload`. Это нужно для того, чтобы у пользователя была возможность найти свою коробку, если он потореят qr-код.
Также на каждой странице есть кнопка `вернутся на главную` которая возвращает на главную страницу.
# Запуск решения:

## Windows

Осуществляется с помощью файла start.bat (у вас должен быть устновлен maven, docker и java 21, docker так же должен быть запущен).

## Linux

Осуществляется с помощью файла start.bat (у вас должен быть устновлен maven, docker и java 21).

* 1.Перейдите в корень проекта и откройте терминал

* 2.Дайте право на выполнение chmod +x start.sh

* 3.Запустите ./start.sh
