package com.tensquare.tensquare_base.controller;

import com.tensquare.tensquare_base.pojo.Label;
import com.tensquare.tensquare_base.service.LabelService;
import com.tensquare.tensquare_common.entity.PageResult;
import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 标签控制层
 */
@Controller
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @Autowired
    private IdWorker idWorker;

    //查询全部标签
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result findAll() {
        List<Label> list = labelService.findAll();
        return new Result(true, StatusCode.OK, "查询全部列表成功", list);
    }

    //根据id查询标签
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result findById(@PathVariable String id) {
        Label label = labelService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询标签成功", label);
    }

    //增加标签
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Label label) {
        //设置ID
        label.setId(idWorker.nextId() + "");
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加标签成功");
    }

    //修改标签
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public Result update(@PathVariable String id, @RequestBody Label label) {
        label.setId(id);
        labelService.updateById(label);
        return new Result(true, StatusCode.OK, "修改标签成功");
    }

    //删除标签
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除标签成功");
    }

    //条件+分页查询(当前页+页尺寸)
    @RequestMapping(value = "/search/{currentPage}/{size}", method = RequestMethod.POST)
    @ResponseBody
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int currentPage, @PathVariable int size) {
        PageResult<Label> search = labelService.findSearch(searchMap, currentPage, size);
        return new Result(true, StatusCode.OK, "分页条件查询列表成功", search);
    }
}
