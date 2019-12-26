package com.tensquare.tensquare_friend.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_friend.dao.FriendDao;
import com.tensquare.tensquare_friend.pojo.Friend;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FriendService extends ServiceImpl<FriendDao, Friend> {

    /**
     * 添加好友(比如A添加B好友)
     * @param userId
     * @param friendId
     * @return
     */
    @Transactional
    public boolean addFriend(String userId, String friendId) {
        //如果A已经添加了B,则不进行任何操作,返回false
        if (baseMapper.selectCountByUserIdAndFriendId(userId, friendId) > 0) {
            return false;
        } else {
            //添加记录
            Friend friend = new Friend();
            friend.setUserid(userId);
            friend.setFriendid(friendId);
            friend.setIslike("0");
            baseMapper.insertFriend(friend);

            //判断对方是否添加了你
            if (baseMapper.selectCountByUserIdAndFriendId(friendId, userId) > 0) {
                //更新A的记录
                baseMapper.updateLike(userId, friendId, "1");
                //更新B的记录
                baseMapper.updateLike(friendId, userId, "1");
            }
            return true;
        }
    }
}
