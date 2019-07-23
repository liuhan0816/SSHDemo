//需要jquery.easyui.min.js支持.用于自定义校验类型
var getTotalBytes=function(s){
    if( s == null || s ==  "" ) return 0;
    var totalCount = 0;
    for (i = 0; i< s.length; i++) {
        if (s.charCodeAt(i) > 127)
            totalCount += 2;
        else
            totalCount++ ;
    }
    return totalCount;
}
$.extend($.fn.validatebox.defaults.rules, {
    length:{
        validator:function(value,param){
            var len=getTotalBytes(value);
            return len>=param[0]&&len<=param[1];
        },
        message:"输入内容长度必须介于{0}和{1}之间."
    },

    float:{
        validator:function(value) {
            return /^(-?\d+)(\.\d+)?$/i.test(value);
        },
        message:"请输入数字，并保证格式正确"
    },
    alpha:{
        validator:function(value,param){
            if (value){
                return /^[a-zA-Z\u00A1-\uFFFF]*$/.test(value);
            } else {
                return true;
            }
        },
        message:'只能输入字母.'
    },
    alphanum:{
        validator:function(value,param){
            if (value){
                return /^([a-zA-Z\u00A1-\uFFFF0-9])*$/.test(value);
            } else {
                return true;
            }
        },
        message:'只能输入字母和数字.'
    },
    positive_int:{
        validator:function(value,param){
            if (value){
                return /^\d+$/.test(value);
            } else {
                return true;
            }
        },
        message:'只能输入正整数.'
    },
    numeric:{
        validator:function(value,param){
            if (value){
                return /^[0-9]*(\.[0-9]+)?$/.test(value);
            } else {
                return true;
            }
        },
        message:'只能输入数字.'
    },
    chinese:{
        validator:function(value,param){
            if (value){
                return /[^\u4E00-\u9FA5]/g.test(value);
            } else {
                return true;
            }
        },
        message:'只能输入中文'
    }
});