package com.tensquare.tensquare_qa.controller;


import com.tensquare.tensquare_common.entity.PageResult;
import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_qa.pojo.Problem;
import com.tensquare.tensquare_qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;


    //根据标签ID分页查询最新问题列表
    @RequestMapping(value = "/newList/{labelId}/{currentPage}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public Result newList(@PathVariable String labelId, @PathVariable int currentPage, @PathVariable int size) {
        PageResult<Problem> list = problemService.newList(labelId, currentPage, size);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }
}
