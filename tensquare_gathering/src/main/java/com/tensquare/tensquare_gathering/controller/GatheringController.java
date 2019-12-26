package com.tensquare.tensquare_gathering.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_common.util.IdWorker;
import com.tensquare.tensquare_gathering.pojo.Gathering;
import com.tensquare.tensquare_gathering.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gather")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 根据id查询活动
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result findById(@PathVariable String id){
        Gathering gathering = gatheringService.findById(id);
        return new Result(true, StatusCode.OK, "根据id查询活动成功", gathering);
    }

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result findAll(){
        List<Gathering> list = gatheringService.findAll();
        return new Result(true, StatusCode.OK, "查询全部活动成功", list);
    }


    /**
     * 新增活动
     * @param gathering
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Gathering gathering){
        //设置ID
        gathering.setId(idWorker.nextId() + "");
        gatheringService.add(gathering);
        return new Result(true, StatusCode.OK, "增加活动成功");
    }
}
