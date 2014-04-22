DnevnikRuTestApp
================

Приложение позволяет добавлять, редактировать пользователей и просматривать информацию о них. Валидация данных проходит как на клиентской стороне, так и на серверной.

Использованные технологии
--------------
- BackBone
- Jquery
- Bootstrap
- Spring MVC
- Hibernate
- PostgreSQL

Инструкция по развертыванию
--------------
Для создания базы данных нужно выполнить скрипт **/database/create_db.sql** 

Реквизиты доступа редактируются в файле **/src/main/resources/database.properties**

API
--------------
**Заголовки**
- Content-Type:application/json

**Получение списка пользователей**
````javascript
**GET** /api/v1/users
````
Пример ответа:

    [
       {
           "id": 1000,
           "firstName": "Павел",
           "lastName": "Варченко",
           "middleName": "Сергеевич",
           "login": "varpa89",
           "comment": "",
           "gender": "NOT_SELECTED"
       }
    ]


**Создание пользователя**
````javascript
**POST** /api/v1/users
````
Пример запроса:


    {
        "login": "testuser",
        "lastName": "Варченко",
        "firstName": "Павел",
        "middleName": "Сергеевич",
        "birthDate": "02.07.1989",
        "gender": "MALE",
        "comment": "Тестовый комментарий",
        "password": "12345",
        "password2": "12345"
    }
    
    
**Редактирование пользователя**
````javascript
**PUT** /api/v1/users/{id}
````
Пример запроса:


    {
        "login": "testuser",
        "lastName": "Варченко",
        "firstName": "Павел",
        "middleName": "Сергеевич",
        "birthDate": "02.08.1989",
        "gender": "MALE",
        "comment": "Тестовый комментарий изменен"
    }
    
**Удаление пользователя**
````javascript
**DELETE** /api/v1/users/{id}
````

**Получение информации о пользователе**
````javascript
**GET** /api/v1/users/{id}
````
Пример ответа:


    {
       "id": 1001,
       "firstName": "Павел",
       "lastName": "Варченко",
       "middleName": "Сергеевич",
       "login": "testuser",
       "comment": "Тестовый комментарий",
       "birthDate": "02.07.1989",
       "gender": "MALE"
    }

TO DO
--------------
- Более строгая валидация данных

