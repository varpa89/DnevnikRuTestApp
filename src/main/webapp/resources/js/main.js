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