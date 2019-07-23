package com.arthur.web.rest.demo;

import com.arthur.web.utils.HttpUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class RestTest {
	static String domain = "http://127.0.0.1:8083/mavenWeb/rest";
	@Test
	public void string(){
		String url = domain + "/test/string";
		Map param = new HashMap();
		try {
			String result = HttpUtils.doGet(url,param);
			System.out.println("result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void map(){
		String url = domain + "/test/map";
		Map param = new HashMap();
		try {
			String result = HttpUtils.doGet(url,param);
			System.out.println("result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void list(){
		String url = domain + "/test/list";
		Map param = new HashMap();
		try {
			String result = HttpUtils.doGet(url,param);
			System.out.println("result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
