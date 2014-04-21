<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BackBone</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <style>
        .controls input{
            height: 30px;
        }
        .controls select{
            width: 206px;
        }
    </style>
</head>
<body>
<div class="container">
    <%--<h3>Тестовое задание для dnevnik.ru - редактор пользователей</h3>--%>
    <hr/>
    <div class="page"></div>
</div>

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
                <select name="gender"  id="genderId">
                    <option value="NOT_SELECTED" <@ if(user && user.get('gender') == 'NOT_SELECTED') { @> SELECTED <@ }; @> ></option>
                    <option value="MALE" <@ if(user && user.get('gender') == 'MALE') { @> SELECTED <@ }; @> >Мужской</option>
                    <option value="FEMALE" <@ if(user && user.get('gender') == 'FEMALE') { @> SELECTED <@ }; @> >Женский</option>
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

<%--<script src="resources/js/model/usermodel.js"></script>--%>
<script>
    _.templateSettings = {
        interpolate: /\<\@\=(.+?)\@\>/gim,
        evaluate: /\<\@(.+?)\@\>/gim,
        escape: /\<\@\-(.+?)\@\>/gim
    };
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        jqXHR.setRequestHeader('Content-Type', 'application/json');
        jqXHR.setRequestHeader('Accept', 'application/json');
    });

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }

    //Models
    var Users = Backbone.Collection.extend({
        url: '/api/v1/users'
    });
    var User = Backbone.Model.extend({
        urlRoot: '/api/v1/users',
        validate: function (attrs) {
            var errors = [];
            if (!attrs.lastName) {
                errors.push({name: 'lastName', message: 'Необходимо ввести фамилию'});
            }
            if (!attrs.firstName) {
                errors.push({name: 'firstName', message: 'Необходимо ввести имя'});
            }
            if (!attrs.login) {
                errors.push({name: 'login', message: 'Необходимо ввести логин'});
            }
            if (!attrs.id) {
                if (!attrs.password) {
                    errors.push({name: 'password', message: 'Необходимо ввести пароль'});
                }
                if (!attrs.password2) {
                    errors.push({name: 'password2', message: 'Повторите пароль'});
                }
                if (attrs.password && attrs.password.length < 5) {
                    errors.push({name: 'password', message: 'Пароль должен быть не менее 5 символов'});
                }else if (attrs.password && attrs.password2 && attrs.password !== attrs.password2) {
                    errors.push({name: 'password', message: 'Пароли не совпадают'});
                    errors.push({name: 'password2', message: 'Пароли не совпадают'});
                }
            } else {
                if (attrs.password && attrs.password.length < 5) {
                    errors.push({name: 'password', message: 'Пароль должен быть не менее 5 символов'});
                }else if (attrs.password && attrs.password2 && attrs.password !== attrs.password2) {
                    errors.push({name: 'password', message: 'Пароли не совпадают'});
                    errors.push({name: 'password2', message: 'Пароли не совпадают'});
                }
            }

            return errors.length > 0 ? errors : false;
        }
    });

    //Views
    var UserList = Backbone.View.extend({
        el: '.page',
        render: function () {
            var that = this;
            var users = new Users();
            users.fetch({
                success: function (users) {
                    var template = _.template($('#user-list-template').html(), {users: users.models});
                    that.$el.html(template);
                },
                error: function () {
                    console.log("Error while getting users");
                }
            })
        },
        deleteUser: function (options) {
            if (options.id) {
                var user = new User({id: options.id});
                user.destroy({
                    success: function () {
                        router.navigate('', {trigger: true})
                    },
                    error: function () {
                        console.log("Error while removing user");
                    }
                });
            }
        }
    });

    var EditUser = Backbone.View.extend({
        el: '.page',
        render: function (options) {
            var that = this;
            if (options.id) {
                that.user = new User({id: options.id})
                that.user.fetch({
                    success: function (user) {
                        var template = _.template($('#edit-user-template').html(), {user: user});
                        that.$el.html(template);
                        $("#birthDateId").mask("99.99.9999");
                    },
                    error: function (error) {
                        console.log("Error while getting user info");
                    }
                })
            } else {
                var template = _.template($('#edit-user-template').html(), {user: null});
                this.$el.html(template);
                $("#birthDateId").mask("99.99.9999");
            }
        },
        events: {
            'submit .edit-user-form': 'saveUser',
            'click .delete': 'deleteUser'
        },
        saveUser: function (ev) {
            var that = this;
            var userDetails = $(ev.currentTarget).serializeObject();
            var user = new User();
            user.save(userDetails, {
                success: function (user, response) {
                    that.hideErrors();
                    router.navigate('', {trigger: true})
                },
                error: function (model, errors) {
                    if(errors.responseJSON){
                        errors = errors.responseJSON;
                    }
                    that.hideErrors();
                    that.showErrors(errors);
                }
            });
            return false;
        },
        deleteUser: function (ev) {
            this.user.destroy({
                success: function () {
                    router.navigate('', {trigger: true})
                },
                error: function () {
                    console.log("Error while removing user");
                }
            });
            return false;
        },
        showErrors: function (errors) {
            //console.log(errors);
            _.each(errors, function (error) {
                //console.log(error);
                var controlGroup = this.$('.' + error.name);
                controlGroup.addClass('error');
                controlGroup.find('.help-inline').text(error.message);
            }, this);
        },
        hideErrors: function () {
            this.$('.control-group').removeClass('error');
            this.$('.help-inline').text('');
        }
    });
    //Router
    var Router = Backbone.Router.extend({
        routes: {
            '': 'home',
            'new': 'editUser',
            'edit/:id': 'editUser',
            'delete/:id': 'deleteUser'
        }
    });
    var router = new Router();
    router.on('route:home', function () {
        userList.render();
    });
    router.on('route:editUser', function (id) {
        editUser.render({id: id});
    });
    router.on('route:deleteUser', function (id) {
        userList.deleteUser({id: id});
    });

    var userList = new UserList();
    var editUser = new EditUser();

    Backbone.history.start();
</script>
</body>
</html>
