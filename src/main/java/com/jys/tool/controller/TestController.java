package com.jys.tool.controller;

import com.jys.tool.dao.IToolTestDao;
import com.jys.tool.model.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Author: JiaoYuSheng
 * @Date: 2021-11-11 09:41
 * @program Tool
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private IToolTestDao toolTestDao;

    @RequestMapping("/findAll")
    public List<Test> findAll(){
       return toolTestDao.getTest();
    }
}
