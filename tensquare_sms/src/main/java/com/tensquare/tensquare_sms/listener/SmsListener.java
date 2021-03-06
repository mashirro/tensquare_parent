package com.tensquare.tensquare_sms.listener;


import com.aliyuncs.exceptions.ClientException;
import com.tensquare.tensquare_sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 短信监听类
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void sendSms(Map<String, String> map) {
//        System.out.println("手机号："+map.get("mobile"));
//        System.out.println("验证码："+map.get("code"));
        try {
            smsUtil.sendSms(map.get("mobile"), "{\"code\":\"" + map.get("code") + "\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
