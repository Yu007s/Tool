package com.jys.tool.httpTool;

/**
 * @description:
 * @Author: JiaoYuSheng
 * @Date: 2021-11-03 10:20
 * @program Tool
 */
public class Constant {

    /**Strong URL***/

    /***
     * 视频 转 m3u8 URL
     */
    public static String m3u8FormatURL = "https://fs.leke.cn/auth/file/trans/binary.htm?priority=3&appId=microcourse&userId=2706547&schoolId=0&module=3002&decideConfirm=1";

    public static String getLekeResources = "https://fs.leke.cn/auth/file/access/data.htm";

    public static String HTTP_BODY_TYPE_FILE="file";

    public static String HTTP_BODY_TYPE_TEXT="text";

    public static String sendLekeResources_BACK = "/Users/tyq/Desktop/嘉善视频课整理/备份/accessJson.txt";

    public static String sendLekeM3U8_BACK= "/Users/tyq/Desktop/嘉善视频课整理/备份/m3u8RspJson.txt";
}
