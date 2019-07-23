<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!-- easyui核心UI文件 css -->
<link href="<%=basePath%>/js/jquery-easyui-1.7.0/themes/default/easyui.css" rel="stylesheet" type="text/css">
<!-- easyui图标 -->
<link href="<%=basePath%>/js/jquery-easyui-1.7.0/themes/icon.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/tablefrom.css"/>
<div class="orginfopanel">
	<div class="title">
		<h3 style="color: black;">业务信息</h3>
	</div>
	<form id="regForm" action="" method="post">
		<table  cellpadding="0" cellspacing="1" class="formtable" style="width: 100%">
			<tr>
				<td style="text-align: right;width:20%">账户:</td>
				<td style="width:30%">
					<input class="easyui-validatebox" name="user.userCode" id="userCode" placeholder="帐号" type="text" data-options=""/>
				</td>

				<td style="text-align: right;width:20%">密码:</td>
				<td style="width:30%">
					<input class="easyui-validatebox" name="user.password" placeholder="密码" type="password" autocomplete="new-password" data-options=""/>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width:20%">性别:</td>
				<td style="width:30%">
					<select name="user.gender" label="性别">
                        <option value="-1" selected="true">未知</option>
                        <option value="0">女</option>
                        <option value="1">男</option>
                    </select>
                </td>

				<td style="text-align: right;width:20%">重复密码</td>
				<td style="width:30%">
                    <input class="easyui-validatebox" name="user.password2" type="password" autocomplete="new-password" data-options=""/>
				</td>
			</tr>
		</table>
	</form>
	<div id="dlg-buttons" style="text-align:center">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="btn_sub" iconCls="icon-save" onclick="register()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="login()">关闭</a>
	</div>
</div>
<script src="<%=basePath%>/js/jquery.md5.js" type="text/javascript" charset="UTF-8"></script>
<script type="text/javascript">
var basePath="<%=basePath%>";
$().ready(function() {
	//初始化账户密码校验
	$("#regForm").find("[name='user.userCode']").validatebox({
		required: true,
		validType:['length[6,20]','alphanum']
	});
    $("#regForm").find("[name='user.password']").validatebox({
		required: true,
		validType:['length[6,20]','alphanum']
	});
    $("#regForm").find("[name='user.password2']").validatebox({
		required: true,
		validType:['length[6,20]','alphanum']
	});
});
//注册账户
function register(){
    if(!$('#regForm').form('validate')){
        $.messager.alert('提示信息','请补全需求信息');
        return;
    }
	var p=$("#regForm").serializeJson();
	var password= $("#regForm").find("[name='user.password']").val();
	var sign=null;
	if(password){
		var password2=$("#regForm").find("[name='user.password2']").val();
		if(password!=password2){
			var msg="两次密码不一致";
			$.messager.show({
				title: '失败',
				msg: msg
			});
			return;
		}else{
			//对密码进行md5加密
			sign=$.md5(password);
			p["user.password"]=sign;
		}
	}else{
		var msg="密码不能为空";
		$.messager.show({
			title: '失败',
			msg: msg
		});
		return;
	}
	var param = $.extend({}, p);
	var url=basePath+"/user/userManager!register.do";
	$.ajax({
		url : url,
		type : "post",
		data : param,
		cache: false,
		async: false,
		datatype : "json",
		beforeSend:function () {
			$.showProcess(true, '温馨提示', '正在提交用户信息...');
		},
		success:function(data){
			var bean=null;
			try{
				bean = eval("("+data+")");
			}catch(e){
				$.showProcess(false);
				$.messager.alert('警告信息','程序出现错误，请联系客服人员！');
				return;
			}
			$.showProcess(false);
			if (bean.flag!="T"){
				$.messager.show({
					title: '失败',
					msg: bean.content
				});
			} else{
				$.messager.show({
					title: '成功',
					msg: bean.content
				});
				var userCode = $('#userCode').val();
				setLogin(userCode,sign);
				setTimeout(function (){//jquery 高版本string 未定义bug
					//$.messager.alert("温馨提示","123");
					parent.$.modalDialog.handler.dialog('close');
i0n				},1000);

			}
		},onLoadError: function () {
			$.showProcess(false);
			$.messager.alert('温馨提示', '由于网络或服务器太忙，提交失败，请重试！');
		}
	});
}
//返回登陆界面
function login(){
    //window.self.location =basePath;
    parent.$.modalDialog.handler.dialog('close');

}
</script>
