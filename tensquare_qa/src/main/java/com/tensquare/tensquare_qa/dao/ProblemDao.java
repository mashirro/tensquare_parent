package com.tensquare.tensquare_qa.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.tensquare_qa.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProblemDao extends BaseMapper<Problem> {

    /**
     * 根据标签ID分页查询最新问题列表
     * 注意!!: 如果入参是有多个,需要加注解指定参数名才能在xml中取值
     *
     * @param page    分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位
     * @param labelId 标签id
     * @return
     */
    @Select("select * from tb_problem where id in (select problemid from tb_pl where labelid = #{labelId}) order by reply desc")
    IPage<Problem> selectProblemByPage(Page<Problem> page, @Param("labelId") String labelId);
}
