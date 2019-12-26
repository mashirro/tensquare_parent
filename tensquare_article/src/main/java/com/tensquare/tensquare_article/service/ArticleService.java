package com.tensquare.tensquare_article.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_article.dao.ArticleDao;
import com.tensquare.tensquare_article.pojo.Article;
import org.springframework.stereotype.Service;

@Service
public class ArticleService extends ServiceImpl<ArticleDao, Article> {

    /**
     * 文章审核
     * @param id
     */
    public void examine(String id) {
        baseMapper.examine(id);
    }

    /**
     * 文章点赞
     * @param id
     */
    public void thumbup(String id) {
        baseMapper.thumbup(id);
    }
}
