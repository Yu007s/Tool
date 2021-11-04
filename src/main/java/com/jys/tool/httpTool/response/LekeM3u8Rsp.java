package com.jys.tool.httpTool.response;

import lombok.Data;

/**
 * @description: 乐课网视频转换m3u8返回结果对应JavaBean
 * @Author: JiaoYuSheng
 * @Date: 2021-11-03 11:23
 * @program Tool
 */
@Data
public class LekeM3u8Rsp {
    private boolean success;
    private String message;
    private String code;
    private String ticket;
    private String jsessionid;
    private Datas datas;
    private long currentTime;

    @Data
    public class Datas {

        private FileInfo fileInfo;

        @Data
        public class FileInfo {

            private String id;
            private String type;
            private String path;
            private String url;
            private String name;
            private String ext;
            private long size;
            private String width;
            private String height;
            private String poster;
            private long duration;
            private int pageCount;
            private String subs;
            private String detect;
            private String complaintStatus;

        }

    }
}
