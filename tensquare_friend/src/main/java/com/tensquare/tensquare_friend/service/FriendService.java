package com.tensquare.tensquare_friend.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_friend.dao.FriendDao;
import com.tensquare.tensquare_friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
public class FriendService extends ServiceImpl<FriendDao, Friend> {

    private static final String user_client = "TensquareUser";

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 添加好友(比如A添加B好友)
     *
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

            long start = System.currentTimeMillis();

            //调用用户微服务更新A用户的关注数(http://TensquareUser/user/incFollow/1207224877279481856/1)
            restTemplate.postForObject(String.format("http://%s/user/incFollow/%s/%s", user_client, userId, 1), null, String.class);
            //调用用户微服务更新B用户的粉丝数(http://TensquareUser/user/incfans/1206781158491295744/1)
            restTemplate.postForObject(String.format("http://%s/user/incfans/%s/%s", user_client, friendId, 1), null, String.class);

            long end = System.currentTimeMillis();
            System.out.println("restTemplate调用服务:"+(start-end));

            //手动制造异常
            //int i = 1 / 0;

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
