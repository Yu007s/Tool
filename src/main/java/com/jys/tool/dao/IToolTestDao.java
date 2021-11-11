package com.jys.tool.dao;

import com.jys.tool.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 测试dao
 * @Author: JiaoYuSheng
 * @Date: 2021-11-10 20:59
 * @program Tool
 */

public interface IToolTestDao {

    public List<Test> getTest();

}
