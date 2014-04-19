window.User = Backbone.Model.extend({
    urlRoot: "/api/v1/users",
    initialize: function () {
        console.log('New user collection');
        this.users = new UserCollection();
    }
});

window.UserCollection = Backbone.Collection.extend({
    model: User,
    url: "/api/v1/users"
});