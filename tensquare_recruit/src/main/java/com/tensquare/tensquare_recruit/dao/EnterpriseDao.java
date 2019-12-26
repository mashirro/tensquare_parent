package com.tensquare.tensquare_recruit.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_recruit.pojo.Enterprise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 企业Dao
 */
@Mapper
public interface EnterpriseDao extends BaseMapper<Enterprise>{

    //查询热门企业
    List<Enterprise> hotList(String ishot);
}
