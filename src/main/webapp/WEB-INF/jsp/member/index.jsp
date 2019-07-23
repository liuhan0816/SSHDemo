<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../../inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 使basePath由当前文件路径即xxx/jsp 变为xxx,放在jsp<head></head>里-->
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理界面</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/index.css"/>
</head>
<!--
<body class="b">
<div class="bar">
    <button onclick="loginout()" class="bt">退出</button>
</div>
</body>
-->
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">

<!-- 顶部-->
<!--Begin 头部 -->
<div id="pf-hd" region="north" border="false">
    <img src="<%=basePath%>/images/title.jpg" height="100%" width="100%">
    <div class="pf-logo"><img src="<%=basePath%>/images/dragon_left.png" alt="logo" height="100%" width="100%"/></div>
    <!--<div class="pf-name">统</div>-->
    <!--begin 头部导航-->


    <ul class="navHeader">
    </ul>

    <!--end 头部导航-->


    <!--begin 退出-->
    <div class="pf-user">


        <div class="pf-user-photo"> <img src="" alt="" /></div>
        <h4 class="pf-user-name ellipsis" title="AAAA" >

        </h4>
        <h4 class="pf-user-name ellipsis" title="BBB">CCC</h4>
        <a href="javascript:void(0)" class="signOut" onclick="loginout()">[ 退出  ]</a>


    </div>
    <!--end 退出-->

</div>
<!--End 头部  -->
<!-- 左侧-->
<div region="west" split="true"  title="导航菜单" style="width: 160px; padding: 1px;">
<%--
    <jsp:include page="left.jsp"/>
    --%>
</div>


</div>
<!-- 中间-->
<div id="mainPanle" region="center" style="overflow: hidden;">
    <div id="maintabs" class="easyui-tabs" fit="true" border="false">
        <div class="easyui-tab" title="首页"  style="padding:2px; overflow: hidden;">
            <iframe  id="middle" frameborder="0" style="border: 0; width: 100%; height: 99.3%;"></iframe>
        </div>
    </div>
</div>
<!-- 底部 -->

<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>

</div>

</body>
<script type="text/javascript">
    $().ready(function(){
        var userId=getCookie("identify");
        if(userId==""){
            $.messager.show({
                title: '温馨提示',
                msg: '请先登录'
            });
            setTimeout(function (){
                window.location.href="<%=basePath%>/user/userManager!doLogin.do";
            },1000);
        }else{
            $.messager.show({
                title: '温馨提示',
                msg: '进入后台管理界面'
            });
        }
    });
    function loginout(){
        clearLogin();
    }

</script>
</html>