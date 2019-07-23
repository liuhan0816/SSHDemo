<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String rurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	response.sendRedirect(request.getContextPath()+"/member/login/login!doInitialize.do");
%>
