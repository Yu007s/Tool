package com.jys.tool.httpTool;

import com.alibaba.fastjson.JSONObject;
import com.jys.tool.fileTool.FileTool;
import com.jys.tool.httpTool.response.LekeAccessRsp;
import com.jys.tool.httpTool.response.LekeM3u8Rsp;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static String ticket="VFdwWlBRPT07S1Nvdkt5a3FLaTR2S3lzPTsyNjI2";

    private static String baseDir = "/Users/tyq/Desktop/LearnSpaces/Tool/嘉善隔离视频/2021-11-04-4";

    private static int baseCourseId = 22;

    public static AtomicInteger count = new AtomicInteger(21);


    public static void main(String[] args) {
       //batchSendM3u8FormatByDateDir(baseDir);
      batchSendAccessByDateDir(baseDir);
       //System.out.println("#####搞完了，下班吧#####");
    }

    public static void batchSendAccessByDateDir(String dateDir){
        String idDir = dateDir+"/idDir";
        List<String> dirNameList = FileTool.getFileName(idDir);
        for (String dirName : dirNameList){
            batchSendAccess(idDir+"/"+dirName);
        }
    }


    public static void batchSendAccess(String path){
        String content = FileTool.readTxtFile(path);
        String[] strings = StringUtils.split(content,"\n");
        for (String id : strings){
            if (!id.isEmpty()){
               LekeAccessRsp rsp = sendLekeResources(id);
               //生成SQL
               String sql = getJiashanSQL(rsp,path.substring(path.lastIndexOf("/")+1));
               FileTool.writeTxt(sql,baseDir+"sql.txt",true);
            }
        }
    }

    public static String getJiashanSQL(LekeAccessRsp rsp,String fileName){
        String courseName = rsp.getDatas().getFile().getName();
        String jpgURL = rsp.getDatas().getFile().getPoster();
        String m3u8 = rsp.getDatas().getFile().getPages().get(0).getPaths().get(0);
        Integer courseId = Integer.parseInt(fileName) + baseCourseId;
        String s = "INSERT INTO `course`.`js_course_video` ( `courseId`, `title`, `imageUrl`, `videoUrl`, `studyCount`, `createdBy`, `createdOn`, `modifiedBy`, `modifiedOn`, `isDeleted`) VALUES \n" +
                "( '"+courseId+"', '" + courseName + "', '" + jpgURL + "',\n" +
                " '" + m3u8 + "', 2, 888, '2021-11-02 17:03:11', 888, '2021-11-02 17:03:11', 0);\n";
        System.out.println(s);
        return s;
    }

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private static ExecutorService executorDate = Executors.newFixedThreadPool(9);
    /**日期级别目录**/
    public static void batchSendM3u8FormatByDateDir(String dateDir){
        List<String> dirNameList = FileTool.getDirNameByDirPath(dateDir);
        for (String dirName : dirNameList){
            executorDate.submit(new Runnable() {
                @Override
                public void run() {
                    batchSendM3u8Format(dateDir+"/"+dirName);
                }
            });

        }
    }

    /**年级级别目录**/
    public static void batchSendM3u8Format(String path){
        List<File> fileList = FileTool.getFileByDirPath(path);
        for (File  file : fileList){
            if (".mp4".equals(FileTool.getFileSuffix(file.getName()))  ||".flv".equals( FileTool.getFileSuffix(file.getName()) )) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        LekeM3u8Rsp lekeM3u9Rsp = sendM3u8Format(file);
                        String id = lekeM3u9Rsp.getDatas().getFileInfo().getId(); // leke网文件ID
                        String fileName = path.substring(path.lastIndexOf("/"));
                        System.out.println(id + "\n");
                        FileTool.writeTxt(id + "\n", path + "/" + fileName , true);
                        FileTool.writeTxt(id + "\n",  baseDir+"/idDir/"+fileName, true);
                    }
                });
            }
        }
    }


    /**
     * 获取乐课资源信息
     * @param id 资源id
     * @return 资源信息
     */
    public static LekeAccessRsp sendLekeResources(String id){
        Map<String,String> prams = new HashMap<>();
        prams.put("ticket",ticket);
        prams.put("id",id);
        String responseString = "";
        try {
            responseString = HttpUtil.postHttp(Constant.getLekeResources,prams,null,null,null,null);
            FileTool.writeTxt(responseString,"/Users/tyq/Desktop/嘉善视频课整理/备份/accessJson.txt",true);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(responseString,LekeAccessRsp.class);
    }


    public static LekeM3u8Rsp sendM3u8Format(File file){
        Map<String,String> prams = new HashMap<>();
        prams.put("ticket",ticket);
        String responseString = "";
        try {
          responseString = HttpUtil.postHttp(Constant.m3u8FormatURL,prams,null,Constant.HTTP_BODY_TYPE_FILE,file,null);
          FileTool.writeTxt(responseString,"/Users/tyq/Desktop/嘉善视频课整理/备份/m3u8RspJson.txt",true);
          System.out.println(count.addAndGet(-1));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(responseString,LekeM3u8Rsp.class);
    }



}
