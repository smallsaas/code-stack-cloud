package com.jfeat.module.crudcode.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtil {

    public static String post(String postUrl,String json)  {
        try {
            // 3.创建连接与设置连接参数
            URL urlObj = new URL(postUrl);
            HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Charset", "UTF-8");
            // POST请求且JSON数据,必须设置
            httpConn.setRequestProperty("Content-Type", "application/json");
            // 打开输出流,默认是false
            httpConn.setDoOutput(true);
            // 打开输入流,默认是true,可省略
            httpConn.setDoInput(true);
            // 4.从HttpURLConnection获取输出流和写数据
            OutputStream oStream = httpConn.getOutputStream();
            oStream.write(json.getBytes());
            oStream.flush();
            // 5.发起http调用(getInputStream触发http请求)
            if (httpConn.getResponseCode() != 200) {
                throw new Exception("调用服务端异常.");
            }

            // 6.从HttpURLConnection获取输入流和读数据
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream()));
            StringBuffer str = new StringBuffer();
            String tempStr;
            while((tempStr = br.readLine()) != null){
                str.append(tempStr);
            }
            System.out.println("从服务端返回结果: " + str.toString());
            return str.toString();
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}
