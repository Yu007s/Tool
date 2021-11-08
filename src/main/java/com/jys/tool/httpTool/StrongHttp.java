package com.jys.tool.httpTool;

import com.alibaba.fastjson.JSONObject;
import com.jys.tool.fileTool.FileTool;
import com.jys.tool.httpTool.entity.JiaShanCourseVideo;
import com.jys.tool.httpTool.response.LekeAccessRsp;
import com.jys.tool.httpTool.response.LekeM3u8Rsp;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 发送施强接口HTTP
 * @Author: JiaoYuSheng
 * @Date: 2021-11-03 10:16
 * @program Tool
 */
public class StrongHttp {

    /**
     * 获取乐课资源信息
     * @param id 资源id
     * @return 资源信息
     */
    public static LekeAccessRsp sendLekeResources(String id,String ticket){
        Map<String,String> prams = new HashMap<>();
        prams.put("ticket",ticket);
        prams.put("id",id);
        String responseString = "";
        try {
            responseString = HttpUtil.postHttp(Constant.getLekeResources,prams,null,null,null,null);
            FileTool.writeTxt(responseString,Constant.sendLekeResources_BACK,true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(responseString,LekeAccessRsp.class);
    }


    public static LekeM3u8Rsp sendM3u8Format(File file,String ticket,AtomicInteger count){
        Map<String,String> prams = new HashMap<>();
        prams.put("ticket",ticket);
        String responseString = "";
        String fileName= file.getName();
        try {
            System.out.println(Thread.currentThread().getName()+"  :开始上传:  "+fileName);
          responseString = HttpUtil.postHttp(Constant.m3u8FormatURL,prams,null,Constant.HTTP_BODY_TYPE_FILE,file,null);
          FileTool.writeTxt(responseString,Constant.sendLekeM3U8_BACK,true);
          System.out.println(count.addAndGet(-1));
        } catch (URISyntaxException e) {
            System.out.println(Thread.currentThread().getName()+"  :上传失败！！！:  "+fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()+"  :上传失败 ！！！！:  "+fileName);
            e.printStackTrace();
        }
        return JSONObject.parseObject(responseString,LekeM3u8Rsp.class);
    }



}
