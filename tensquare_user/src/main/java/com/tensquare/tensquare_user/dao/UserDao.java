package com.tensquare.tensquare_user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    @Select("select * from tb_user where mobile = #{mobile}")
    User findByMobile(@Param("mobile") String mobile);

    /**
     * 增加粉丝数
     *
     * @param userid
     * @param num
     */
    @Update("update tb_user set fanscount = fanscount + #{num} where id = #{userid}")
    void incFanscount(@Param("userid") String userid, @Param("num") int num);

    /**
     * 增加关注数
     *
     * @param userid
     * @param num
     */
    @Update("update tb_user set followcount = followcount + #{num} where id = #{userid}")
    void incFollowcount(@Param("userid") String userid, @Param("num") int num);
}
