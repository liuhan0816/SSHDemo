package com.arthur.web.rest.demo;

import com.arthur.web.utils.HttpUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class UserTest {
	static String domain = "http://127.0.0.1:8083/mavenWeb/rest";
	@Test
	public void string(){
		String url = domain + "/user/checkId";
		Map params = new HashMap();
		String userId="liuhan";
		params.put("userId", userId);
		try {
			String result = HttpUtils.doGet(url,params);
			System.out.println("result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
