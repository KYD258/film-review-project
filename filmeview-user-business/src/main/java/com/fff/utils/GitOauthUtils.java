package com.fff.utils;


import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class GitOauthUtils {

    public  String doPost(String url,Map<String,Object> content) {
        String reusult ="";
        BufferedReader in =null;
        CloseableHttpResponse response=null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request =new HttpPost();
        try{
            request.setURI(new URI(url));
            List<NameValuePair> list =new ArrayList<NameValuePair>();
            if(MapUtils.isNotEmpty(content))
            {
                for(Map.Entry<String,Object> entry:content.entrySet())
                {
                    list.add(new BasicNameValuePair(entry.getKey(),(String)entry.getValue()));
                }
            }
            request.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
            request.setHeader("Content-Type","application/x-www-form-urlencoded");

             response =client.execute(request);
            int statusCode =response.getStatusLine().getStatusCode();
            if(HttpStatus.SC_OK==statusCode) {
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
                String line ;
                while ((line=in.readLine())!=null){
                    reusult+=line;
                }

            }
        }catch(Exception e) {
           e.printStackTrace();
        }finally{
            if (client!=null){
                try {
                    ( client).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response!=null){
                try {
                    (response).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return reusult;
    }



    public  String doGet(String url,Map<String,Object> content) {
        String result = null;
        //请求地址
        //获取请求参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        if(MapUtils.isNotEmpty(content))
        {
            for(Map.Entry<String,Object> entry:content.entrySet())
            {
                list.add(new BasicNameValuePair(entry.getKey(),(String)entry.getValue()));
            }
        }
        // 获取httpclient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String parameStr = null;
        try {
            parameStr = EntityUtils.toString(new UrlEncodedFormEntity(list));
            //拼接参数
            StringBuffer sb = new StringBuffer();
            sb.append(url);
            sb.append("?");
            sb.append(parameStr);
            //创建get请求
            HttpGet httpGet = new HttpGet(sb.toString());
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpGet.setConfig(requestConfig);
            // 提交参数发送请求
            response = httpclient.execute(httpGet);

            // 得到响应信息
            int statusCode = response.getStatusLine().getStatusCode();
            // 判断响应信息是否正确
            if (statusCode != HttpStatus.SC_OK) {
                // 终止并抛出异常
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
            EntityUtils.consume(entity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭所有资源连接
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }
    @Value("${url_getUser}")
    private String url;

//    @RabbitListener(queues = "topic.message")
//    public void saveUserInDbAndSession(String[] token){
//        Map map=new HashMap();
//        map.put(token[0],token[1]);
//        String s = this.doGet(url, map);
//        System.out.println(s.toString());
//    }


}
