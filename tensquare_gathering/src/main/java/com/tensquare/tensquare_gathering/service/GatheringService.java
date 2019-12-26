package com.tensquare.tensquare_gathering.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_gathering.dao.GatheringDao;
import com.tensquare.tensquare_gathering.pojo.Gathering;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatheringService extends ServiceImpl<GatheringDao, Gathering> {

    /**
     * 根据id查询活动
     *
     * @param id
     * @return
     */
    @Cacheable(value = "gatheringCache", key = "#id")
    public Gathering findById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询全部
     * unless: 如果结果的大小为0,方法的结果将不会被缓存。如果我们不提供键,默认情况下将是"",或者如果可用的话，使用方法参数来计算键。
     *
     * @return
     */
    @Cacheable(value = "allGatheringCache", unless = "#result.size()==0")
    public List<Gathering> findAll() {
        return baseMapper.selectList(null);
    }

    /**
     * 新增活动(增删改时都要清除缓存!!!!)
     * 如果allEntries=true，则缓存中的所有条目都将被删除
     * 这里注意返回值类型-->Gathering(不然@CachePut会报错:Cache 'gatheringCache' does not allow 'null' values.)
     * @param gathering
     */
    @Caching(
            put = {@CachePut(value = "gatheringCache", key = "#gathering.id")},
            evict = {@CacheEvict(value = "allGatheringCache", allEntries = true)}
    )
    public Gathering add(Gathering gathering) {
        baseMapper.insert(gathering);
        return baseMapper.selectById(gathering.getId());
    }
}
