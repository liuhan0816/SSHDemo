//需要jquery easyui 支持
(function($){
    //form对象转json对象
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                /**
                 if($.isArray(serializeObj[this.name])){
                        serializeObj[this.name].push(this.value);
                   }else{
                      serializeObj[this.name]=[serializeObj[this.name],this.value];
                   }
                 **/
                serializeObj[this.name]=serializeObj[this.name]+","+this.value;
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };

    //进度条
    $.extend({
        showProcess: function (isShow, title, msg) {
            if (!isShow) {
                $.messager.progress('close');
                return;
            }
            $.messager.progress({
                title: title,
                msg: msg
            });
        }
    });
    //创建一个模式化的dialog
    $.modalDialog = function(options) {
        if ($.modalDialog.handler == undefined) {// 避免重复弹出
            var opts = $.extend({
                title : '',
                width : 840,
                height : 680,
                modal : true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                onClose : function() {
                    $.modalDialog.handler = undefined;
                    $(this).window('destroy');
                }
            }, options);
            opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
            var t=$('<div/>').window(opts);
            //if ($.browser.msie) {
             //   if ($.browser.version <7) {
             //      t.parent().bgiframe();
             //   }
            //}
            return $.modalDialog.handler = t;
        }
    };
})(jQuery);