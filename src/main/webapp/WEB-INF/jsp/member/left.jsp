<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div id="nav" class="easyui-accordion" fit="true" border="false">
<%
String functionName = "";
if (!menuList.isEmpty()) {
	for (int i = 0; i < menuList.size(); i++) {
		T4plFuncDef templ = (T4plFuncDef) menuList.get(i);
		String parentId = templ.getFunctionid();
%>
	<div title="<%=templ.getFunctionname()%>" iconCls="folder">
		<%if (templ != null && templ.getFunctionList() != null
			&& templ.getFunctionList().size() > 0) {
		%>
		<ul>
			<%
				Set tempSet = templ.getFunctionList();
				Iterator it = tempSet.iterator();
				while (it.hasNext()) {
					T4plFuncDef temp = (T4plFuncDef) it.next();
					if (parentId.equals(temp.getParentid())) {
						String serviceName="";
						String furl = "#";
						if (temp.getFunctionurl().indexOf("#") < 0) {
							serviceName=request.getServerName();
							if(serviceName.equals("localhost")||serviceName.equals("127.0.0.1")){
								if(temp.getFunctionurl().indexOf("logscmweb")>0){
									furl =  temp.getFunctionurl();
								}else{
									furl =  "/haScmWeb-gb"+temp.getFunctionurl();
								}
							}else{
								furl =  temp.getFunctionurl();
							}
						}
			%>
			<li>
				<div onclick="addTab('<%=temp.getFunctionname()%>','<%=furl%>','pictures')" title="<%=temp.getFunctionname()%>" iconCls="pictures">
					<a> <span class="icon pictures">&nbsp;</span> <span class="nav"><%=temp.getFunctionname()%></span></a>
				</div>
			</li>
			<%		}
				} 
			%>
		</ul>
		<%	} 
		%>
	</div>
	<%	}
	}
	%>
</div>