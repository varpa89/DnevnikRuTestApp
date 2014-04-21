window.CustomAlert = Backbone.View.extend({
    el: '.custom-alert',
    render: function(alertClass,resultText){
        var that = this;
        var template = _.template($('#custom-alert-template').html(), {alertClass: alertClass, resultText: resultText});
        var obj = that.$el.html(template);
        setTimeout(function () {
            obj.children().remove();
        }, 5000);
    }
});