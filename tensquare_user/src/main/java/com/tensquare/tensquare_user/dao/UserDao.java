package com.tensquare.tensquare_user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    @Select("select * from tb_user where mobile = #{mobile}")
    User findByMobile(@Param("mobile") String mobile);
}
