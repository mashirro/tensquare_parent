package com.tensquare.tensquare_user.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.tensquare_user.dao.UserDao;
import com.tensquare.tensquare_user.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService extends ServiceImpl<UserDao, User> {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送短信验证码
     *
     * @param mobile
     */
    public void sendSms(String mobile) {
        //1.生成6位短信验证码
        int code = new Random().nextInt(999999);
        if (code < 100000) {
            code = code + 100000;
        }
        System.out.println(mobile + "收到的验证码是: " + code);
        //2.将生成的验证码放入redis(5分钟过期)
        redisTemplate.opsForValue().set("smsCode_" + mobile, code + "", 5, TimeUnit.MINUTES);
        //3.将验证码和手机号发送到rabbitMQ中
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code + "");
        rabbitTemplate.convertAndSend("sms", map);
    }

    /**
     * 用户注册
     *
     * @param user
     * @param code 用户填写的验证码
     */
    public void add(User user, String code) {
        //1.从缓存中提取验证码
        String verifyCode = redisTemplate.opsForValue().get("smsCode_" + user.getMobile());
        if (verifyCode == null) {
            throw new RuntimeException("请点击获取短信验证码!");
        }
        //2.判断验证码是否输入正确
        if (!verifyCode.equals(code)) {
            throw new RuntimeException("验证码输入不正确!");
        }
        //3.插入
        baseMapper.insert(user);
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile) {
        return baseMapper.findByMobile(mobile);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     */
    public void deleteById(String id) {
        baseMapper.deleteById(id);
    }

    /**
     * 增加粉丝数
     *
     * @param userid
     * @param num
     */
    public void incFanscount(String userid, int num) {
        baseMapper.incFanscount(userid, num);
    }

    /**
     * 增加关注数
     *
     * @param userid
     * @param num
     */
    public void incFollowcount(String userid, int num) {
        baseMapper.incFollowcount(userid, num);
    }
}
