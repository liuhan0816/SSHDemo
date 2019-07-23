package com.arthur.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//使用了httplclient4.3以上.3.x,4.x~4.3以下和4.3以上使用方法不同
public class HttpUtils {
	public static int SocketTimeout = 60000;// ms
	public static int ConnectTimeout = 60000;
	public static int ConnectionRequestTimeout = 60000;
	public static String DEFAULT_CHARSET = "UTF-8";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	
	static Log logger = LogFactory.getLog(HttpUtils.class);
	// 设置请求和传输超时时间
	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SocketTimeout)
			.setConnectTimeout(ConnectTimeout).setConnectionRequestTimeout(ConnectionRequestTimeout).build();
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String doGet(String url, Map params) throws IOException, URISyntaxException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// HTTP Get请求
		HttpGet httpGet = new HttpGet(buildGetUrl(url, buildQuery(params, DEFAULT_CHARSET)));
		httpGet.setConfig(requestConfig);
		String res = "";
		try {
			// 执行请求
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if (200 == response.getStatusLine().getStatusCode()) {
				res = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
			} else {
				logger.error("响应码:" + response.getStatusLine());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpGet.releaseConnection();
				httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return res;
			}
		}
		return res;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		List<BasicNameValuePair> nameValuePairs = new ArrayList<>();
		for (String key : params.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key,params.get(key).toString()));
		}

		// HTTP Get请求
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);  
		String res = "";
		try {
			UrlEncodedFormEntity urlEncodedFormEntity=new UrlEncodedFormEntity(nameValuePairs,DEFAULT_CHARSET);
			urlEncodedFormEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(urlEncodedFormEntity);
			// 执行请求
			CloseableHttpResponse response = httpClient.execute(httpPost);
			if (200 == response.getStatusLine().getStatusCode()) {
				res = EntityUtils.toString(response.getEntity());
			} else {
				logger.error("响应码:" + response.getStatusLine());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				httpPost.releaseConnection();
				httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return res;
			}
		}
		return res;
	}
	
	/**
	 * 将map数据集转换成 A=?&B=?&C=?形式的字符串
	 * @param params
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String buildQuery(Map params, String charset)
			throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuffer query = new StringBuffer();
		// Set entries = params.entrySet();
		boolean hasParam = false;

		Iterator<?> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) it.next();
			String name = (String) entry.getKey();
			String value = (String) entry.getValue();
			if (StringUtils.isNotEmpty(name)) {
				if (value == null) {
					value = "";
				}
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(
						URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}
	
	/**
	 * 拼接url和参数
	 * @param strUrl
	 * @param query
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private static URI buildGetUrl(String strUrl, String query)
			throws IOException, URISyntaxException {
		URI url = new URI(strUrl);
		if (StringUtils.isEmpty(query)) {
			return url;
		}

		if (StringUtils.isEmpty(url.getQuery())) {
			if (strUrl.endsWith("?")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "?" + query;
			}
		} else {
			if (strUrl.endsWith("&")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "&" + query;
			}
		}
		return new URI(strUrl);
	}
}