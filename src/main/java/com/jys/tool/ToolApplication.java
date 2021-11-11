package com.jys.tool;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @Author: JiaoYuSheng
 * @Date: 2021-11-10 20:41
 * @program Tool
 */
@SpringBootApplication
@MapperScans({
        @MapperScan("com.jys.tool.dao")
})
public class ToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolApplication.class,args);
        System.out.println("===============Tool启动成功==============");
    }
}
