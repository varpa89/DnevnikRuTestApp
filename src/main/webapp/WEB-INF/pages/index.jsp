<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактор пользователей. Тестовое задание для dnevnik.ru (выполнил Варченко П.С.)</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <style>
        .controls input {
            height: 30px;
        }

        .controls select {
            width: 206px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Редактор пользователей</h3>
    <h5>Тестовое задание для <a href="http://dnevnik.ru/" class="btn-link">dnevnik.ru</a></h5>
    <hr/>
    <div class="custom-alert"></div>
    <div class="page"></div>
</div>

<script type="text/template" id="custom-alert-template">
    <div class="alert <@= alertClass @>">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <@= resultText @>
    </div>
</script>

<script type="text/template" id="user-list-template">
    <a href="#/new" class="btn btn-primary">Новый пользователь</a>
    <hr/>
    <table class="table stripped">
        <thead>
        <th>Логин</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th></th>
        </thead>
        <tbody>
        <@ _.each(users, function(user){ @>
        <tr>
            <td><@= user.get('login') @></td>
            <td><@= user.get('lastName') @></td>
            <td><@= user.get('firstName') @></td>
            <td><@= user.get('middleName') @></td>
            <td>
                <a href="#/edit/<@= user.id @>" class="btn btn-info">Редактировать</a>
                <a href="#/delete/<@= user.id @>" class="btn btn-danger">Удалить</a>
            </td>
        </tr>
        <@}); @>
        </tbody>
    </table>
</script>

<script type="text/template" id="edit-user-template">
    <form class="edit-user-form form-horizontal">
        <legend><@= user ? 'Редактирование':'Создание' @> пользователя</legend>
        <div class="control-group login">
            <label for="loginId" class="control-label">Логин:</label>

            <div class="controls">
                <input type="text" id="loginId" name="login" value="<@= user ? user.get('login') : '' @>">
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group lastName">
            <label for="lastNameId" class="control-label">Фамилия:</label>

            <div class="controls">
                <input type="text" id="lastNameId" name="lastName" value="<@= user ? user.get('lastName') : '' @>">
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group firstName">
            <label for="firstNameId" class="control-label">Имя:</label>

            <div class="controls">
                <input type="text" id="firstNameId" name="firstName" value="<@= user ? user.get('firstName') : '' @>">
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group middleName">
            <label for="middleNameId" class="control-label">Отчество:</label>

            <div class="controls">
                <input type="text" id="middleNameId" name="middleName"
                       value="<@= user ? user.get('middleName') : '' @>">
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group birthDate">
            <label for="birthDateId" class="control-label">Дата рождения:</label>

            <div class="controls">
                <input type="text" id="birthDateId" name="birthDate"
                       value="<@= (user && user.get('birthDate') !== undefined) ? user.get('birthDate') : '' @>">
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group gender">
            <label for="genderId" class="control-label">Пол:</label>

            <div class="controls">
                <select name="gender" id="genderId">
                    <option value="NOT_SELECTED"
                    <@ if(user && user.get('gender') == 'NOT_SELECTED') { @> SELECTED <@ }; @> ></option>
                    <option value="MALE"
                    <@ if(user && user.get('gender') == 'MALE') { @> SELECTED <@ }; @> >Мужской</option>
                    <option value="FEMALE"
                    <@ if(user && user.get('gender') == 'FEMALE') { @> SELECTED <@ }; @> >Женский</option>
                </select>
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group comment">
            <label for="commentId" class="control-label">Комментарий:</label>

            <div class="controls">
                <textarea rows="3" id="commentId" name="comment"><@= user ? user.get('comment') : '' @></textarea>
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group password">
            <label for="passwordId" class="control-label">Пароль:</label>

            <div class="controls">
                <input type="password" id="passwordId" name="password">
                <span class="help-inline"></span>
            </div>
        </div>
        <div class="control-group password2">
            <label for="password2Id" class="control-label">Повтор пароля:</label>

            <div class="controls">
                <input type="password" id="password2Id" name="password2">
                <span class="help-inline"></span>
            </div>
        </div>
        <hr/>
        <button type="submit" class="btn btn-success"><@= user ? 'Обновить' : 'Создать' @></button>
        <@ if(user){ @>
        <input type="hidden" name="id" value="<@= user.id @>"/>
        <button type="button" class="btn btn-danger delete">Удалить</button>
        <@ }; @>
        <a href="#" class="btn btn-link">Назад</a>
    </form>
</script>

<script src="resources/js/lib/jquery-1.11.0.min.js"></script>
<script src="resources/js/lib/underscore-min.js"></script>
<script src="resources/js/lib/backbone-min.js"></script>
<script src="resources/js/lib/jquery.maskedinput.min.js"></script>
<script src="resources/js/lib/bootstrap.min.js"></script>

<script src="resources/js/lib/utils.js"></script>
<script src="resources/js/model/usermodel.js"></script>
<script src="resources/js/view/edituser.js"></script>
<script src="resources/js/view/userlist.js"></script>
<script src="resources/js/view/alert.js"></script>
<script src="resources/js/main.js"></script>

</body>
</html>
