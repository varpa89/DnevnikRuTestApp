<%--
  Created by IntelliJ IDEA.
  User: varchenko
  Date: 18.04.14
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BackBone</title>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.1.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Тестовое задание для dnevnik.ru - редактор пользователей</h1>
    <hr/>
    <div class="page"></div>
</div>

<script type="text/template" id="user-list-template">
    <table class="table stripped">
        <thead>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th></th>
        </thead>
        <tbody>
            <@ _.each(users, function(user){ @>
               <tr>
                     <td></td>
                     <td></td>
                     <td></td>
                     <td></td>
               </tr>
            <@}); @>
        </tbody>
    </table>
</script>

<script src="resources/js/lib/jquery-1.7.2.min.js"></script>
<script src="resources/js/lib/underscore-min.js"></script>
<script src="resources/js/lib/backbone-min.js"></script>

<%--<script src="resources/js/model/usermodel.js"></script>--%>
<script>
    _.templateSettings = {
        interpolate: /\<\@\=(.+?)\@\>/gim,
        evaluate: /\<\@(.+?)\@\>/gim,
        escape: /\<\@\-(.+?)\@\>/gim
    };
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        jqXHR.setRequestHeader('Content-Type', 'application/json');
    });
    var Users = Backbone.Collection.extend({
        url: '/api/v1/users'
    });

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
        }
    });

    var Router = Backbone.Router.extend({
        routes: {
            '': 'home'
        }
    });
    var router = new Router();
    router.on('route:home', function () {
        userList.render();
    });

    var userList = new UserList();

    Backbone.history.start();
</script>
</body>
</html>
