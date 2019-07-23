<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../../inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 使basePath由当前文件路径即xxx/jsp 变为xxx,放在jsp<head></head>里-->
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/login.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台登陆</title>
</head>
<body class="b" onkeydown="keyLogin(event);">
	<form id="loginForm" method="post" autocomplete="off">
		<div class="bar">
			<button type="reset" class="bt">重置</button>
			<button type="button" class="bt" onclick="doRegisterView()">注册</button>
		</div>
		<div class="lg">
			<div class="lg_top"></div>
			<div class="lg_main">
				<div class="lg_m_1">
					<div class="icon-ur icon-position"></div>
					<input class="input-style input-position easyui-validatebox" name="user.userCode" id="userCode" placeholder="帐号" type="text" data-options=""/>
					<div class="icon-pw icon-position"></div>
					<input class="input-style input-position easyui-validatebox" name="user.password" placeholder="密码" type="password" autocomplete="new-password" data-options=""/>
				</div>
			</div>
			<div class="lg_foot">
				<button type="button" class="bn" onclick="doSubmit()">测试登录</button>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript" src="<%=basePath%>/js/jquery.md5.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/mytools.js"></script>
<script type="text/javascript">
var basePath="<%=basePath%>";

$().ready(function(){
	//初始化账户密码校验
	$("#loginForm").find("[name='user.userCode']").validatebox({
		required: true,
		validType:['length[6,20]','alphanum']
	});
	$("#loginForm").find("[name='user.password']").validatebox({
		required: true,
		validType:['length[6,20]','alphanum']
	});
});
function doSubmit() {
	if(!$('#loginForm').form('validate')){
		$.messager.alert('提示信息','请补全需求信息');
		return;
	}
	var p =$('#loginForm').serializeJson();
	var password = $("#loginForm").find("[name='user.password']").val();
	var sign = $.md5(password);
	p["user.password"]=sign;

	var url=basePath+"/user/userManager!login.do";
	var param = $.extend({}, p);
	$.ajax({
		url : url,
		type : "post",
		data : param,
		cache: false,
		async: false,
		datatype : "json",
		beforeSend:function () {
			$.showProcess(true, '温馨提示', '正在校验账户密码...');
		},
		success:function(data){
			$.showProcess(false);
			var bean=null;
        	try{
				bean = eval("("+data+")");
     		}catch(e){
     			$.messager.alert('警告信息','程序出现错误，请联系客服人员！');
     			return;
     		}
     		if (bean.flag=="T"){
				$.messager.show({
					title: '成功',
					msg: bean.content
				});
				var userCode =  $("#loginForm").find("[name='user.userCode']").val();
				setLogin(userCode,sign);
				setTimeout(function (){//jquery 高版本string 未定义bug
					//$.messager.alert("温馨提示","123");
					window.location.href=basePath+"/login/login!doInitialize.do";
				},1000);
	        } else{
				$.messager.show({
					title: '失败',
					msg: bean.content
				});
			}
     	},onLoadError: function () {
			$.showProcess(false);
	    	$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
	    }
	});
}
//注册界面
function doRegisterView(){
    var title="注册界面";
    var url=basePath+'/user/userManager!doRegister.do';

    //var iframe = '<iframe src='+url+' frameborder=0 style=border:0;width:100%;height:99.4%;></iframe>';
    parent.$.modalDialog({
        title: title,
        width: 1024,
        height: 400,
        modal: true,
        shadow: false,
        maximizable:true,
        minimizable:false,
        resizable:true,
        //content:iframe,
		href:url,
        closable: true,
        onOpen:function(){

        }
    });
}
//键盘按键时候调用--当按键为回车时,调用登陆函数
function keyLogin(e){
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code==13) {  //回车键的键值为13
		doSubmit();  //登陆事件
	}
}

</script>
</html>