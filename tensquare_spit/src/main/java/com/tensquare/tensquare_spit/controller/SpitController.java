package com.tensquare.tensquare_spit.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_common.util.IdWorker;
import com.tensquare.tensquare_spit.pojo.Spit;
import com.tensquare.tensquare_spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    public SpitService spitService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部记录
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result findAll() {
        List<Spit> list = spitService.findAll();
        return new Result(true, StatusCode.OK, "查询全部成功", list);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "根据ID查询成功", spitService.findById(id));
    }

    /**
     * 新增
     *
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Spit spit) {
        //主键
        spit.set_id(idWorker.nextId() + "");
        spitService.add(spit);
        return new Result(true, StatusCode.OK, "新增成功");
    }


    /**
     * 修改
     *
     * @param spit
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public Result updateById(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public Result deleteById(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
