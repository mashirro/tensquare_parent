package com.tensquare.tensquare_base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_base.dao.LabelDao;
import com.tensquare.tensquare_base.pojo.Label;
import com.tensquare.tensquare_common.entity.PageResult;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 标签业务逻辑类
 */
@Service
public class LabelService extends ServiceImpl<LabelDao, Label> {
    //查询全部标签
    public List<Label> findAll() {
        return baseMapper.selectList(null);
    }

    //根据id查询标签
    public Label findById(String id) {
        //return baseMapper.selectById(id);
        return baseMapper.selectByPrimaryKey(id);
    }

    //增加标签
    public void add(Label label) {
        baseMapper.insert(label);
    }

    //删除标签
    public void deleteById(String id) {
        baseMapper.deleteById(id);
    }

    //分页+条件查询
    public PageResult<Label> findSearch(Map searchMap, int currentPage, int size) {
        Page<Label> page = new Page<>(currentPage, size);
        QueryWrapper<Label> queryWrapper = createSpecification(searchMap);
        IPage<Label> labelIPage = baseMapper.selectPage(page, queryWrapper);
        return new PageResult<Label>(labelIPage.getTotal(),labelIPage.getRecords());
    }


    //构建查询条件
    private QueryWrapper<Label> createSpecification(Map searchMap){
        //开启状态
        String state = searchMap.get("state").toString();
        //标签名称
        String labelname = searchMap.get("labelname").toString();

        //条件构造器QueryWrapper继承自AbstractWrapper,自身的内部属性entity也用于生成where条件
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();

        if(state!=null&&!"".equals(state)){
            queryWrapper.eq("state",state);
        }
        if(labelname!=null&&!"".equals(labelname)){
            //LIKE '%值%'
            queryWrapper.like("labelname",labelname);
        }
        return queryWrapper;
    }
}
