package com.jys.tool.httpTool.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 嘉善空中课堂—素质课程
 * @Author: JiaoYuSheng
 * @Date: 2021-09-08 11:15
 * @program leke-course
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JiaShanCourse  implements Serializable {

    private String imageUrl;

    private String courseName;

    private Long gradeId;

    private String gradeName;

    private Date startTime;

    private Date endTime;

    private Integer type;

    private Long id;

    private Long createdBy;

    private Date createdOn;

    private Long modifiedBy;

    private Date modifiedOn;

    private Boolean isDeleted;
}
