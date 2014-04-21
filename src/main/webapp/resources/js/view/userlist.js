window.UserList = Backbone.View.extend({
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
                alert.render('alert-error', 'При получении списка пользователей произошла ошибка');
            }
        })
    },
    deleteUser: function (options) {
        if (options.id) {
            var user = new User({id: options.id});
            user.destroy({
                success: function () {
                    alert.render('alert-success', 'Пользователь успешно удален');
                    router.navigate('', {trigger: true})
                },
                error: function () {
                    alert.render('alert-error', 'При удалении пользователя произошла ошибка');
                }
            });
        }
    }
});