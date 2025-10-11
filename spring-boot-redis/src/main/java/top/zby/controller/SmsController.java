package top.zby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zby.Service.SmsService;
import top.zby.common.Result;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("/send")
    public Result<Boolean> sendSms(@RequestParam String phone) {
        boolean result = smsService.sendSms(phone);
        return result ? Result.ok(result) : Result.error();
    }
}
