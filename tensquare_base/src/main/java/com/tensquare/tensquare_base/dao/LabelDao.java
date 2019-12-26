package com.tensquare.tensquare_base.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_base.pojo.Label;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签数据访问接口
 */
@Mapper
public interface LabelDao extends BaseMapper<Label>{

    Label selectByPrimaryKey(String id);
}
