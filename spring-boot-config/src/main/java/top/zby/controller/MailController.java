package top.zby.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<ApiResponse> sendMail(@RequestBody Mail mail) {
        try {
            String result = mailService.send(mail);
            return ResponseEntity.ok(ApiResponse.success("邮件发送成功", result));
        } catch (Exception e) {
            log.error("邮件发送失败", e);
            return ResponseEntity.ok(ApiResponse.fail(HttpStatus.BAD_REQUEST, "邮件发送失败: " + e.getMessage()));
        }
    }

    @PostMapping("/sendHtml")
    ResponseEntity<ApiResponse> sendHtmlMail(@RequestBody Mail mail) {
        try {
            String result = mailService.sendHtmlMail(mail);
            return ResponseEntity.ok(ApiResponse.success("邮件发送成功", result));
        } catch (Exception e) {
            log.error("邮件发送失败", e);
            return ResponseEntity.ok(ApiResponse.fail(HttpStatus.BAD_REQUEST, "邮件发送失败: " + e.getMessage()));
        }
    }
}
