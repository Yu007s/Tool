package com.jys.tool.httpTool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @description:
 * @Author: JiaoYuSheng
 * @Date: 2021-11-03 15:03
 * @program Tool
 */
public class HttpUtil {
    public static String postHttp(String url, Map<String,String> prams, Map<String,String> headers, String BodyType, File file, String stringBody) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost();

        /**添加参数**/
        if ( headers != null && !headers.isEmpty()) {
            headers.forEach((k, v) -> {
                httpPost.setHeader(k, v);
            });
        }
        /**添加URL**/
        URIBuilder uriBuilder = new URIBuilder(url);
        /**拼借接Query参数**/
        if ( prams != null && !prams.isEmpty()) {
            prams.forEach((k, v) -> {
                uriBuilder.addParameter(k, v);
            });
        }
        httpPost.setURI(uriBuilder.build());

        if (BodyType == Constant.HTTP_BODY_TYPE_FILE){
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setContentType(ContentType.MULTIPART_FORM_DATA);
            builder.setCharset(StandardCharsets.UTF_8);
            builder.addBinaryBody("file", file);
            builder.setMode(HttpMultipartMode.RFC6532);
            ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), StandardCharsets.UTF_8);
            HttpEntity httpEntity = builder.build();
            httpPost.setEntity(httpEntity);
        }

        if (BodyType == Constant.HTTP_BODY_TYPE_TEXT){
            StringEntity stringEntity = new StringEntity(stringBody);
            httpPost.setEntity(stringEntity);
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        BufferedReader in = new BufferedReader(new InputStreamReader( httpResponse.getEntity().getContent(), "UTF-8"));

        StringBuffer buffer = new StringBuffer();
        String line = null;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
