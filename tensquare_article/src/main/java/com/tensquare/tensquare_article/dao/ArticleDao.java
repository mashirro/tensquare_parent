package com.tensquare.tensquare_article.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_article.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ArticleDao extends BaseMapper<Article>{
    /**
     * 文章审核
     * @param id
     */
    @Update("update tb_article set state='1' where id= #{id}")
    void examine(String id);

    /**
     * 文章点赞
     * @param id
     */
    @Update("update tb_article set thumbup=thumbup+1 where id= #{id}")
    void thumbup(String id);
}
