package com.tensquare.tensquare_recruit.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_recruit.pojo.Recruit;
import com.tensquare.tensquare_recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    //查询推荐职位列表(以创建日期降序排序，只查询前4条记录)
    @RequestMapping(value = "/search/recommend", method = RequestMethod.GET)
    @ResponseBody
    public Result recommend() {
        List<Recruit> list = recruitService.findTop4RecommendOrderByCreatetimeDesc("2");
        return new Result(true, StatusCode.OK,"查询成功",list);
    }


    //最新职位列表(查询状态不为0并以创建日期降序排序，查询前12条记录)
    @RequestMapping(value = "/search/newList",method = RequestMethod.GET)
    @ResponseBody
    public Result newList(){
        List<Recruit> list = recruitService.findTop12NewListOrderByCreatetimeDesc("0");
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

}
