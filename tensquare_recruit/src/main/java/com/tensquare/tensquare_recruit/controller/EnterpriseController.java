package com.tensquare.tensquare_recruit.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_recruit.pojo.Enterprise;
import com.tensquare.tensquare_recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;


    //查询热门企业
    @RequestMapping(value = "/search/hotList",method = RequestMethod.GET)
    @ResponseBody
    public Result hotList(){
        List<Enterprise> list = enterpriseService.hotList();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
