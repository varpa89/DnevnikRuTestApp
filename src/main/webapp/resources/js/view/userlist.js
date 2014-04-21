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