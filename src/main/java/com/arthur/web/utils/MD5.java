package com.arthur.web.utils;

import java.security.MessageDigest;

public class MD5 {
	/**
	 * md5加密
	 * @param s
	 * @return
	 */
	public final static String encode(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();// 把参数 s 转换成byte类型数组
			// 得到一个MessageDigest 类型的变量mdTemp，提供信息摘要算法的功能
			MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
			mdTemp.update(strTemp);// 更新摘要
			byte[] md = mdTemp.digest();// 这个方法应该是加密后返回的byte数组
			int j = md.length; // 记录md的长度
			char str[] = new char[j * 2]; // 存储结果用
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				// byte0无符右移4位,取与获得高四位(<=15),
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				// 获取低四位,这也是char str[] = new char[j * 2];的原因,
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}