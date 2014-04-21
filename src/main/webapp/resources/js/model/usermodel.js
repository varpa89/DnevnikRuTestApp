window.Users = Backbone.Collection.extend({
    url: '/api/v1/users'
});
window.User = Backbone.Model.extend({
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