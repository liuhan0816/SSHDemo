//新增登陆cookie
var setLogin=function(userCode,sign){
	//var exp = new Date();//有效期限
	//exp.setTime(exp.getTime() + 60 * 1000 * 60 * 24); //24小时
	document.cookie = "identify=" + userCode+"; path=/"	//+ ";expires=" + exp.toGMTString();
	document.cookie = "sign=" + sign+"; path=/"			// + ";expires=" + exp.toGMTString();
}
//清除登陆cookie
var clearLogin=function(){
	var exp=new Date();
	exp.setTime(exp.getTime()-10000);//使cookie过期
	document.cookie = "identify=" + ";path=/" + ";expires=" + exp.toGMTString();
	document.cookie = "sign=" + ";path=/" + ";expires=" + exp.toGMTString();
    window.location.reload(true);
}
/* 获取指定cookie */
var getCookie=function(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name)
            return arr[1];
    }
    return "";
}