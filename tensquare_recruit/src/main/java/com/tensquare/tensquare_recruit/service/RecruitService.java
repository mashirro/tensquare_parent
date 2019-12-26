package com.tensquare.tensquare_recruit.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_recruit.dao.RecruitDao;
import com.tensquare.tensquare_recruit.pojo.Recruit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitService extends ServiceImpl<RecruitDao, Recruit> {

    //查询推荐职位列表(以创建日期降序排序，只查询前4条记录)
    public List<Recruit> findTop4RecommendOrderByCreatetimeDesc(String state) {
        return baseMapper.findTop4RecommendOrderByCreatetimeDesc(state);
    }

    //最新职位列表(查询状态不为0并以创建日期降序排序，查询前12条记录)
    public List<Recruit> findTop12NewListOrderByCreatetimeDesc(String state) {
        return baseMapper.findTop12NewListOrderByCreatetimeDesc(state);
    }
}
