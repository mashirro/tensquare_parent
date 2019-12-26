package com.tensquare.tensquare_qa.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_common.entity.PageResult;
import com.tensquare.tensquare_qa.dao.ProblemDao;
import com.tensquare.tensquare_qa.pojo.Problem;
import org.springframework.stereotype.Service;

@Service
public class ProblemService extends ServiceImpl<ProblemDao, Problem> {

    //根据标签ID分页查询最新问题列表
    public PageResult<Problem> newList(String labelId, int currentPage, int size) {
        Page<Problem> page = new Page<>(currentPage, size);

        //这里我们使用自定义xml分页
        IPage<Problem> iPage = baseMapper.selectProblemByPage(page, labelId);
        return new PageResult<>(iPage.getTotal(), iPage.getRecords());
    }


}
