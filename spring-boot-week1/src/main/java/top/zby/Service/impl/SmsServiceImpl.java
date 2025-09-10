package top.zby.Service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.zby.Service.SmsService;
import top.zby.config.SmsConfig;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsConfig smsConfig ;
    @Override
    public void sendSms(String phone) {
        //验证码
        int code = ThreadLocalRandom.current().nextInt(0000, 9999);
        log.info("发送验证码：{}", code);

        //取得配置信息
        String serverIp = smsConfig.getServerIp();
        String port = smsConfig.getPort();
        String accountSId = smsConfig.getAccountSId();
        String accountToken = smsConfig.getAccountToken();
        String appId = smsConfig.getAppId();
        String templateId = smsConfig.getTemplateId();

        //创建sdk对象
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, port);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String[] data = {String.valueOf(code), "1"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(phone, templateId, data, "1234", UUID.randomUUID().toString());
        if ("000000" .equals(result.get("statusCode"))){
            HashMap<String, Object> data1 = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data1.keySet();
            for (String key : keySet) {
                Object object = data1.get(key);
                log.info("{}:{}", key, object);
            }
        }else{
            log.error("错误码：{}，错误信息：{}", result.get("statusCode"), result.get("statusMsg"));
        }
    }
}
