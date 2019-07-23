package com.arthur.web.rest.impl;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/test")  
public class HelloRest {
	@GET 
	@Path("/string")  
    @Produces(MediaType.TEXT_PLAIN)  
    public String sayHello1() {  
        return "Hello Jersey";  
    }  

	@GET  
	@Path("/map")  
    @Produces(MediaType.APPLICATION_JSON)  
    public Map sayHello2() {  
		Map map=new HashMap();
		map.put("hello", "Hello Jersey");
        return map;  
    }  
	
	@GET  
	@Path("/list")  
    @Produces(MediaType.APPLICATION_JSON)  
    public List sayHello3() {  
		List list=new ArrayList();
		list.add("Hello Jersey");
        return list;  
    }  
}
