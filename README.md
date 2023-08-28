# Дипломная работа «Облачное хранилище»
## Описание проекта
Задача — разработать REST-сервис. Сервис должен предоставить REST-интерфейс для загрузки файлов и вывода списка уже загруженных файлов пользователя.
Все запросы к сервису должны быть авторизованы. Заранее подготовленное веб-приложение (FRONT) должно подключаться к разработанному сервису без доработок, а также использовать функционал FRONT для авторизации, загрузки и вывода списка файлов пользователя.

## Реализация

- Приложение разработано с использованием Spring Boot
- Использован сборщик пакетов maven
- Для запуска используется docker, docker-compose
- Код размещен на github
- Код покрыт unit тестами с использованием mockito
- Добавлены интеграционные тесты с использованием testcontainers

## Описание и запуск FRONT

1. Установите nodejs (версия не ниже 19.7.0) на компьютер, следуя инструкции https://nodejs.org/ru/download/current.
2. Скачайте FRONT https://github.com/netology-code/jd-homeworks/tree/master/diploma/netology-diplom-frontend (JavaScript).
3. Перейдите в папку FRONT приложения и все команды для запуска выполняйте из неё.
4. Следуя описанию README.md FRONT проекта, запустите nodejs-приложение (npm install, npm run serve).
5. Далее нужно задать url для вызова своего backend-сервиса.
   - В файле .env FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: VUE_APP_BASE_URL=http://localhost:8080.
     1. Нужно указать корневой url вашего backend, к нему frontend будет добавлять все пути согласно спецификации 
     2. Для VUE_APP_BASE_URL=http://localhost:8080 при выполнении логина frontend вызовет http://localhost:8080/login
   - Запустите FRONT снова: npm run serve. 
   - Изменённый url сохранится для следующих запусков.
6. По умолчанию FRONT запускается на порту 8080 и доступен по url в браузере http://localhost:8080.
   - Если порт 8080 занят, FRONT займёт следующий доступный (8081). После выполнения npm run serve в терминале вы увидите, на каком порту он запустился.

## Авторизация приложения

FRONT-приложение использует header auth-token, в котором отправляет токен (ключ-строка) для идентификации пользователя на BACKEND. Для получения токена нужно пройти авторизацию на BACKEND и отправить на метод /login логин и пароль. В случае успешной проверки в ответ BACKEND должен вернуть json-объект с полем auth-token и значением токена. Все дальейшие запросы с FRONTEND, кроме метода /login, отправляются с этим header. Для выхода из приложения нужно вызвать метод BACKEND /logout, который удалит/деактивирует токен. Последующие запросы с этим токеном будут не авторизованы и вернут код 401.

## Логины и пароли, необходимые для авторизации

Login: `address1@mail.ru`, password: `p111`

Login: `address2@mail.ru`, password: `p222`

Login: `address3@mail.ru`, password: `p333`

