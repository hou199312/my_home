package com.houzizhen.home.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class HttpClientUtil {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000) // 设置连接超时时间
            .setSocketTimeout(10000)  // 设置读取超时时间
            .build();

    /**
     * 发送GET请求
     * @param url 请求的URL
     * @return 响应的字符串内容
     * @throws IOException
     */
    public static String sendHttpGetRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            } else {
                throw new IOException("GET request failed with status: " + statusCode);
            }
        }
    }

    /**
     * 发送POST请求
     * @param url 请求的URL
     * @param requestBody 请求体内容
     * @return 响应的字符串内容
     * @throws IOException
     */
    public static String sendHttpPostRequest(String url, String requestBody) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        if (requestBody != null) {
            httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "UTF-8");
            } else {
                throw new IOException("POST request failed with status: " + statusCode);
            }
        }
    }

    // 关闭HttpClient，通常在应用关闭时调用
    public static void shutdown() {
        try {
            httpClient.close();
        } catch (IOException e) {
            log.error("Error closing HttpClient: {}", e.getMessage());
        }
    }
}
