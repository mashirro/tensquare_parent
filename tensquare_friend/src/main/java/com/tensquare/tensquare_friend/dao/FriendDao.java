package com.tensquare.tensquare_friend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.tensquare_friend.pojo.Friend;
import org.apache.ibatis.annotations.*;


@Mapper
public interface FriendDao extends BaseMapper<Friend> {

    /**
     * 查询A是否已经添加B为好友了
     * @param userId
     * @param friendId
     * @return
     */
    @Select("select count(1) from tb_friend where userid = #{userId} and friendid = #{friendId}")
    int selectCountByUserIdAndFriendId(@Param("userId") String userId, @Param("friendId") String friendId);

    /**
     * 更新是否互相关注
     * @param userId    用户id
     * @param friendId  添加好友id
     * @param isLike
     */
    @Update("update tb_friend set islike = #{isLike} where userid = #{userId} and friendid = #{friendId}")
    void updateLike(@Param("userId") String userId, @Param("friendId") String friendId, @Param("isLike") String isLike);

    /**
     * 插入记录
     * @param friend
     */
    @Insert("insert into tb_friend(userid,friendid,islike) values(#{userid},#{friendid},#{islike})")
    void insertFriend(Friend friend);
}
