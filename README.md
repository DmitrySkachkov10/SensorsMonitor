Запуск системы

Для запуска в консоли выполните команду: docker-compose up -d в директории проекта

Импорт данных

Sql скрипты не требуются. Данные автоматически будут загружены из папки ddl и доступны в базе данных в Docker. Для удобства работы с базой вы можете войти в pgAdmin по адресу: localhost:82

Логин: admin@admin.com
Пароль: dmitry
Пароль к базе: dmitry

Запросы API

Аутентификация 

POST запрос: http://localhost:8081/user/login
Тело запроса:

{
    "login": "admin",
    "password": "admin"
}

Для Аутентификация как viewer используйте логин и пароль – viewer. После успешного выполнения запроса будет выдан JWT токен.

Загрузка данных

GET запрос: http://localhost:8083/basic/data/load
Этот запрос возвращает виды type и unit для фронтенда.

Авторизация для последующих запросов
Для всех последующих запросов необходимо добавить в заголовок Authorization значение «Bearer » с вашим JWT токеном.

Получение сенсоров

GET запрос: http://localhost:8083/sensor?page=1

	Добавление сенсора

POST запрос: http://localhost:8083/sensor/add
Тело запроса:
{
    "name": "fdskfs",
    "model": "sdgnelg",
    "rangeFrom": 0,
    "rangeTo": 100,
    "location": "kitchen",
    "description": "For smth",
    "unit": "°C",
    "type": "Temperature"
}

Значения type и unit должны соответствовать тем, что были получены во втором запросе, так как пользователь не может ввести свои значения.

Обновление сенсора

PUT запрос: http://localhost:8083/sensor/c5f8f971-9beb-44f0-9aba-88c79292fc95/update/1713871008852
Тело запроса:
{
    "name": "rename",
    "model": "newName",
    "rangeFrom": 20,
    "rangeTo": 70,
    "location": "toilet",
    "description": "For nothing",
    "unit": "°C",
    "type": "Temperature"
}

UUID и время нужно взять из GET запроса для получения данных. Время используется для реализации оптимистической блокировки.

Удаление сенсора

DELETE запрос: http://localhost:8083/sensor/c5f8f971-9beb-44f0-9aba-88c79292fc95/delete

