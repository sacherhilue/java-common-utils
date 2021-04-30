package com.wuwei.util;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author wuwei
 * @date 2018/10/11 10:40
 */
public class HttpClient {

    /**
     * 将文件提交至文件服务器
     *
     * @param url  请求地址
     * @param file 文件对象
     * @return result 上传结果
     */
    public static String postFile(String url, File file) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            //超时设置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//设置连接超时时间，单位毫秒
                    .setConnectionRequestTimeout(3000)//设置从连接池获取Connection超时时间，单位毫秒
                    .setSocketTimeout(5000)//请求获取数据的超时时间(即响应时间)，单位毫秒
                    .build();
            httpPost.setConfig(requestConfig);
            //参数设置
            HttpEntity httpEntity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(Charset.forName("UTF-8"))
                    .addBinaryBody("file", file)
                    .build();
            httpPost.setEntity(httpEntity);
            //执行请求
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity);
                // 消耗掉response
                EntityUtils.consume(resEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpclient);
            HttpClientUtils.closeQuietly(response);
        }
        return result;
    }
}
