package com.jfeat.module.apicode.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/5/10 09:48
 * @author: hhhhhtao
 */
@Slf4j
public class HttpUtils {

    /**
     * GET请求
     * @param url 请求路径
     * @return
     */
    public static JSONObject get(String url) {

        JSONObject result = null;
        // 创建get请求
        HttpGet httpGet = new HttpGet(url);
        // 创建http客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // http客户端执行get请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            // 在response中获取实体
            HttpEntity responseEntity = httpResponse.getEntity();
            result = JSON.parseObject(EntityUtils.toString(responseEntity));
            if (httpResponse.getCode() != 200) {
                log.error("api返回错误信息，method:GET，url:{}, 返回信息：{}", url, result);
            }
        } catch (IOException | ParseException e ) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * post请求，携带token, body为json格式
     * @param url 请求路径
     * @param body 参数Json
     * @param token 令牌
     */
    public static JSONObject post(String url, JSONObject body, String token) {

        JSONObject result = null;
        // 创建post请求
        HttpPost httpPost = new HttpPost(url);
        // 创建请求body
        HttpEntity httpEntity = new StringEntity(body.toJSONString(), ContentType.APPLICATION_JSON);
        // 设定到post请求中
        httpPost.setEntity(httpEntity);
        // 设定token
        if (StringUtils.isNotBlank(token)) {
            if (token.contains("Bearer ")) {
                httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
            } else {
                httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            }
        }
        // 创建http客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // http客户端执行post请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            // 获取返回结果
            HttpEntity responseEntity = httpResponse.getEntity();
            result = JSON.parseObject(EntityUtils.toString(responseEntity));
            if (httpResponse.getCode() != 200) {
                log.error("api返回错误信息，method:POST，url:{}，请求body：{}, 返回信息：{}", url, body, result);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * post请求，不携带token
     * @param url 请求路径
     * @param body 参数Json
     */
    public static JSONObject post(String url, JSONObject body) {
        return post(url, body, null);
    }

}
