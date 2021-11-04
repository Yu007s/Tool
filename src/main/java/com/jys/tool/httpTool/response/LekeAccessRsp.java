package com.jys.tool.httpTool.response;

import lombok.Data;

import java.util.List;

/**
 * @description: 乐课网根据id 获取 资源信息 返回类
 * @Author: JiaoYuSheng
 * @Date: 2021-11-02 20:00
 * @program leke-course
 */
@Data
public class LekeAccessRsp {

    private boolean success;
    private String message;
    private String code;
    private String ticket;
    private String jsessionid;
    private Datas datas;
    private long currentTime;
    private Integer courseId;

    @Data
    public class Datas {
        private File file;

        @Data
        public class File {

            private String id;
            private String name;
            private String path;
            private long size;
            private String ext;
            private String type;
            private long duration;
            private String poster;
            private int pageCount;
            private int progress;
            private int status;
            private String errCode;
            private String errInfo;
            private List<Pages> pages;
            private String detect;
            private String complaintStatus;

            @Data
            public class Pages {

                private int index;
                private int status;
                private String errCode;
                private String errInfo;
                private List<String> paths;
                private String swfPath;
                private String width;
                private String height;
                private int quality;

            }
        }
    }

}
