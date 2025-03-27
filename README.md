Основной функционал приложения - загрузка фотографии содержимого коробки и названии и получении в ответ qr-кода для дальнешей распечатки и наклейки на коробку, чтобы потом можно было просто отсканировать qr-код и увидеть что внутри, без необходимости открывать коробку. Дело в том что коробки зачастую сложены друг на друге и чтобы проверить, например, самую нижнюю из них необходимо будет снять все коробки что лежат на ней. С помощью qr-кода можно будет быстро посмотреть что лежит к каждой коробке и найти нужную.
Проект написан на базе Spring Boot с использованием Thymeleaf для работы с html, Hibernate для работы с бд, в качестве СУБД используется PosgreSQL.
Запуск решения:

Windows

Осуществляется с помощью файла start.bat (у вас должен быть устновлен maven, docker и java 21, docker так же должен быть запущен).

Linux

Осуществляется с помощью файла start.bat (у вас должен быть устновлен maven, docker и java 21).

1.Перейдите в корень проекта и откройте терминал

2.Дайте право на выполнение chmod +x start.sh

3.Запустите ./start.sh
