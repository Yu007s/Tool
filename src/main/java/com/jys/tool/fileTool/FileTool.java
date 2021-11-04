package com.jys.tool.fileTool;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: JiaoYuSheng
 * @Date: 2021-11-03 11:35
 * @program Tool
 */
public class FileTool {


    /**
     * 通过文件夹获取文件下所以文件
     *
     * @return dirPath
     */
    public static List<File> getFileByDirPath(String dirPath) {
        List<File> fileList = new ArrayList<>();
        if (null != dirPath && !"".equals(dirPath)) {
            File file = new File(dirPath);
            //判断文件或目录是否存在
            if (!file.exists()) {
                System.out.println(("【" + dirPath + " not exists】"));
            }
            //获取该文件夹下所有的文件
            File[] fileArray = file.listFiles();
            File fileName = null;
            for (int i = 0; i < fileArray.length; i++) {
                fileName = fileArray[i];
                //判断此文件是否存在
                if (fileName.isDirectory()) {
                    System.out.println(("【目录：" + fileName.getName() + "】"));
                } else {
                    fileList.add(fileName);
                    //System.out.println((fileName.getName()));
                }
            }
        }
        return fileList;
    }

    /**
     * 获取文件夹下 的 文件夹名字
     * @return dirPath
     */
    public static List<String> getDirNameByDirPath(String dirPath) {
        List<String> listFileNames = new ArrayList<>();
        if (null != dirPath && !"".equals(dirPath)) {
            File file = new File(dirPath);
            //判断文件或目录是否存在
            if (!file.exists()) {
                System.out.println(("【" + dirPath + " not exists】"));
            }
            //获取该文件夹下所有的文件
            File[] fileArray = file.listFiles();
            File fileName = null;
            for (int i = 0; i < fileArray.length; i++) {
                fileName = fileArray[i];
                //判断此文件是否存在
                if (fileName.isDirectory()) {
                    listFileNames.add(fileName.getName());
                }
            }
        }
        return listFileNames;
    }

    /**
     * 通过文件夹获取文件下所以文件名
     *
     * @param dirPath
     * @return
     */
    public static List<String> getFileName(String dirPath) {
        List<String> listFileNames = new ArrayList<>();
        if (null != dirPath && !"".equals(dirPath)) {
            File file = new File(dirPath);
            //判断文件或目录是否存在
            if (!file.exists()) {
                System.out.println(("【" + dirPath + " not exists】"));
            }
            //获取该文件夹下所有的文件
            File[] fileArray = file.listFiles();
            File fileName = null;
            for (int i = 0; i < fileArray.length; i++) {
                fileName = fileArray[i];
                //判断此文件是否存在
                if (fileName.isDirectory()) {
                    System.out.println(("【目录：" + fileName.getName() + "】"));
                } else {
                    listFileNames.add(fileName.getName());
                    //System.out.println((fileName.getName()));
                }
            }
        }
        return listFileNames;
    }


    /**
     * 写入文件
     *
     * @param content  内容
     * @param filePath 文件路径
     * @param append   是否向后添加 ture :往后继续写 false ： 覆盖掉原来的
     */
    public static void writeTxt(String content, String filePath, Boolean append) {
        FileOutputStream fileOutputStream = null;
        File file = new File(filePath);
        try {
            if (file.exists()) {
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file, append);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     *
     * @param filePath 文件路径
     */
    public static String readTxtFile(String filePath) {
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lineTxt + "\n");
                    System.out.println(lineTxt);
                }
                read.close();
                return stringBuffer.toString();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取文件后缀
     **/
    public static String getFileSuffix(String fileName) {
        if (fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        } else {
            return "";
        }
    }

}
