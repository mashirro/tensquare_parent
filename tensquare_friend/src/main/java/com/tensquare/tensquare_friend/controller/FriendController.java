package com.tensquare.tensquare_friend.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import com.tensquare.tensquare_friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 在推荐好友列表中添加好友,比如A添加B
     *
     * @param friendId
     * @return
     */
    @RequestMapping(value = "/like/{friendId}", method = RequestMethod.PUT)
    @ResponseBody
    public Result addFriend(@PathVariable String friendId, HttpServletRequest request) {
        Claims user_claims = (Claims) request.getAttribute("user_claims");
        if (user_claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        } else {
            //判断A是否已经添加B为好友了
            if (!friendService.addFriend(user_claims.getId(), friendId)) {
                return new Result(false, StatusCode.REPERROR, "已经添加此好友");
            } else {
                return new Result(true, StatusCode.OK, "操作成功");
            }

        }
    }
}
