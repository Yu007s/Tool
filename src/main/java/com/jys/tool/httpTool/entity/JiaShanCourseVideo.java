package com.jys.tool.httpTool.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 嘉善空中课堂-素质课程-单课
 * @Author: JiaoYuSheng
 * @Date: 2021-09-08 11:24
 * @program leke-course
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JiaShanCourseVideo   {


    private Long courseId;


    private String videoUrl;


    private String imageUrl;


    private Long studyCount;


    private String title;



    private Long id;


    private Long createdBy;


    private Date createdOn;


    private Long modifiedBy;


    private Date modifiedOn;


    private Boolean isDeleted;

}
