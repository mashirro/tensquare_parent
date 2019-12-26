package com.tensquare.tensquare_recruit.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_recruit.dao.EnterpriseDao;
import com.tensquare.tensquare_recruit.pojo.Enterprise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService extends ServiceImpl<EnterpriseDao, Enterprise> {

    //查询热门企业
    public List<Enterprise> hotList() {
        return baseMapper.hotList("1");
    }
}
