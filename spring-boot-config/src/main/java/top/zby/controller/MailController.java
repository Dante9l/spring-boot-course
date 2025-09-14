package top.zby.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        ApiResponse response = mailService.send(mail);
        return response.getCode() == HttpStatus.OK ? ResponseEntity.ok(response) : ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("/sendHtml")
    ResponseEntity<ApiResponse> sendHtmlMail(@RequestBody Mail mail) {
        ApiResponse response = mailService.sendHtmlMail(mail);
        return response.getCode() == HttpStatus.OK ? ResponseEntity.ok(response) : ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping(value ="/sendAttachments" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ApiResponse> sendAttachmentsMail(@Valid @RequestPart("mail") Mail mail, @RequestPart("files") MultipartFile[] file) {
        ApiResponse response = mailService.sendAttachmentsMail(mail, file);
        return response.getCode() == HttpStatus.OK ? ResponseEntity.ok(response) : ResponseEntity.status(response.getCode()).body(response);
    }
}
