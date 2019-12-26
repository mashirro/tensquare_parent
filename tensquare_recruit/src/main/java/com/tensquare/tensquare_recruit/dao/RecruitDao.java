package com.tensquare.tensquare_recruit.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_recruit.pojo.Recruit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitDao extends BaseMapper<Recruit>{

    //查询推荐职位列表(以创建日期降序排序，只查询前4条记录)
    List<Recruit> findTop4RecommendOrderByCreatetimeDesc(String state);

    //最新职位列表(查询状态不为0并以创建日期降序排序，查询前12条记录)
    List<Recruit> findTop12NewListOrderByCreatetimeDesc(String state);
}
