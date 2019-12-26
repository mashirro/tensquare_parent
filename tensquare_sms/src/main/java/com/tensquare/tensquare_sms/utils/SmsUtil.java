package com.tensquare.tensquare_sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.tensquare.tensquare_sms.properties.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 短信工具类
 */
@Component
public class SmsUtil {

    @Autowired
    private SmsProperties smsProperties;

    /**
     * 发送短信
     * @param mobileNum     手机号
     * @param paramJson     参数(JSON格式)
     * @return
     * @throws ClientException
     */
    public void sendSms(String mobileNum, String paramJson) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsProperties.getAccessKeyId(), smsProperties.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //1.接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", mobileNum);
        //2.短信签名名称。
        request.putQueryParameter("SignName", smsProperties.getSignName());
        //3.短信模板ID
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());
        //4.短信模板变量对应的实际值，JSON格式。
        request.putQueryParameter("TemplateParam", paramJson);

        CommonResponse commonResponse = client.getCommonResponse(request);
        String data = commonResponse.getData();
        System.out.println(data);
    }

}