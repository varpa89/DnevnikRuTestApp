window.EditUser = Backbone.View.extend({
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
                    alert.render('alert-error', 'При сохранении данных произошла ошибка');
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
        var successMessage =  '';
        if(userDetails.id){
            successMessage="Данные пользователя обновлены успешно";
        } else{
            successMessage="Новый пользователь добавлен успешно";
        }
        user.save(userDetails, {
            success: function (user, response) {
                that.hideErrors();
                alert.render('alert-success', successMessage);
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
                alert.render('alert-success', 'Пользователь успешно удален');
                router.navigate('', {trigger: true})
            },
            error: function () {
                alert.render('alert-error', 'При удалении пользователя произошла ошибка');
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