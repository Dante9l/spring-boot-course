package top.zby.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.Service.MailService;
import top.zby.common.ApiResponse;
import top.zby.model.Mail;

@RestController
@Slf4j
@RequestMapping("/mail")
public class MailController {
    @Resource
    private MailService mailService;
    @PostMapping("/send")
    ApiResponse sendMail(@RequestBody Mail mail) {
        mailService.send(mail);
        return ApiResponse.success();
    }
}
