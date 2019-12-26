package com.tensquare.tensquare_article.controller;


import com.tensquare.tensquare_article.service.ArticleService;
import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章审核
     * @param id
     * @return
     */
    @RequestMapping(value = "/examine/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Result examine(@PathVariable String id){
        articleService.examine(id);
        return new Result(true, StatusCode.OK, "审核成功！");
    }

    /**
     * 文章点赞
     * @param id
     * @return
     */
    @RequestMapping(value = "/thumbup/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public  Result thumbup(@PathVariable String id){
        articleService.thumbup(id);
        return new Result(true, StatusCode.OK, "点赞成功！");
    }
}
