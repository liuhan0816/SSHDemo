<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 使basePath由当前文件路径即xxx/jsp 变为xxx,放在jsp<head></head>里-->
<%--
<base href="<%=basePath%>">
--%>
<!-- 引入jQuery 核心库-->
<script src="<%=basePath%>/js/jquery-3.4.1/jquery-3.4.1.js" type="text/javascript" charset="utf-8"></script>
<!-- easyui核心库 -->
<script src="<%=basePath%>/js/jquery-easyui-1.7.0/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<!-- easyui核心UI文件 css -->
<link href="<%=basePath%>/js/jquery-easyui-1.7.0/themes/default/easyui.css" rel="stylesheet" type="text/css">
<!-- easyui图标 -->
<link href="<%=basePath%>/js/jquery-easyui-1.7.0/themes/icon.css" rel="stylesheet" type="text/css">
<!--
<link href="<%=basePath%>/js/jquery-easyui-1.7.0/demo/demo.css" rel="stylesheet" type="text/css">
-->
<!-- easyui国际化文件 -->
<script src="<%=basePath%>/js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>

<!-- 自定义cookie库 -->
<script src="<%=basePath%>/js/cookie.js" type="text/javascript" charset="UTF-8"></script>
<!-- 自定义的校验类型库 -->
<script src="<%=basePath%>/js/my-validatebox.js" type="text/javascript" charset="UTF-8"></script>