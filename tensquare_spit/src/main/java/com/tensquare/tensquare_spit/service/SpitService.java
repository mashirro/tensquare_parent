package com.tensquare.tensquare_spit.service;


import com.tensquare.tensquare_spit.dao.SpitDao;
import com.tensquare.tensquare_spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 吐槽业务逻辑类
 */

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    /**
     * 查询全部记录
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     * @return
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 增加
     * @param spit
     */
    public void add(Spit spit){
        //save()方法:插入或更新
        spitDao.save(spit);
    }

    /**
     * 修改
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }
}
