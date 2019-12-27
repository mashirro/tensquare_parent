package com.tensquare.tensquare_user.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_common.util.IdWorker;
import com.tensquare.tensquare_common.util.JwtUtil;
import com.tensquare.tensquare_user.pojo.User;
import com.tensquare.tensquare_user.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JwtUtil jwtUtil;

    /**
     * 获取短信验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    @ResponseBody
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }

    /**
     * 用户注册
     *
     * @return
     */
    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody User user, @PathVariable String code) {
        user.setId(idWorker.nextId() + "");
        user.setFollowcount(0);         //关注数
        user.setFanscount(0);           //粉丝数
        user.setOnline(0L);             //在线时长
        user.setRegdate(new Date());    //注册日期
        user.setUpdatedate(new Date()); //更新日期
        user.setLastdate(new Date());   //最后登录日期
        //密码加密start
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        //密码加密end
        userService.add(user, code);
        return new Result(true, StatusCode.OK, "注册成功");
    }


    /**
     * 用户手机号登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String mobile, String password) {
        //根据手机号查找用户
        User user = userService.findByMobile(mobile);
        //如果用户不为空并且密码匹配,则登陆成功,否则登陆失败
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            //生成token并返回
            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
            Map map = new HashMap<>();
            map.put("token", token);
            map.put("nickname", user.getNickname());    //用户昵称
            return new Result(true, StatusCode.OK, "登陆成功", map);
        } else {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable String id, HttpServletRequest request) {
//        //获取头信息
//        String token = request.getHeader("token");
//        if(token==null){
//            return new Result(false,StatusCode.ACCESSERROR,"权限不足");
//        }
//        Claims claims = jwtUtil.parseJWT(token);
//        if(claims==null || !"admin".equals(claims.get("roles"))){
//            return new Result(false,StatusCode.ACCESSERROR,"权限不足");
//        }
        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "权限不足");
        }
        userService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    /**
     * 增加粉丝数目
     *
     * @param userid
     * @param num
     */
    @RequestMapping(value = "/incfans/{userid}/{num}", method = RequestMethod.POST)
    @ResponseBody
    public Result incFanscount(@PathVariable String userid, @PathVariable int num) {
        userService.incFanscount(userid, num);
        return new Result(true, StatusCode.OK, "操作成功");
    }

    /**
     * 增加关注数目
     *
     * @param userid
     * @param num
     */
    @RequestMapping(value = "/incFollow/{userid}/{num}", method = RequestMethod.POST)
    @ResponseBody
    public Result incFollowcount(@PathVariable String userid, @PathVariable int num) {
        userService.incFollowcount(userid, num);
        return new Result(true, StatusCode.OK, "操作成功");
    }
}
