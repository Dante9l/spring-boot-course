package top.zby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.Service.SmsService;

@RestController
public class SmsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("/sendSms")
    public void sendSms(String phone) {
        smsService.sendSms(phone);
    }
}
