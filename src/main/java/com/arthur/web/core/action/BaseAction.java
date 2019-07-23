package com.arthur.web.core.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Controller
@Scope("prototype")
public class BaseAction extends ActionSupport implements ServletResponseAware, ServletRequestAware  {
	protected HttpServletResponse response;
	protected HttpServletRequest request;

	protected  void setBean(String key,Object value){
		ActionContext.getContext().put(key, value);
	}
	
	protected Object getBean(String key){
		return ActionContext.getContext().get(key);
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * 以对话框形式反馈消息
	 * @throws IOException 
	 */
	public void executeInfo(Boolean flag) throws IOException{
		StringBuilder josn = new StringBuilder();
		if(flag){
			josn.append("{\"successMsg\":\"操作成功!\"}");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(josn.toString());
		}else{
			josn.append("{\"errorMsg\":\"操作失败!\"}");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(josn.toString());
		}
	}
}
