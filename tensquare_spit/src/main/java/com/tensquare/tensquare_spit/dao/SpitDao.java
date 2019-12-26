package com.tensquare.tensquare_spit.dao;


import com.tensquare.tensquare_spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 数据访问层
 */
public interface SpitDao extends MongoRepository<Spit, String> {
}
