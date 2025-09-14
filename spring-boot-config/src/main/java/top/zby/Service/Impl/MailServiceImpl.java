package top.zby.Service.Impl;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zby.Service.MailService;
import top.zby.common.ApiResponse;
import top.zby.model.Mail;

import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private Mail mail;

    @Override
    public ApiResponse send(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        try {
            javaMailSender.send(message);
            return ApiResponse.success("邮件发送成功");
        } catch (MailException e) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, "邮件发送失败: " + e.getMessage());
        }
    }

    @Override
    public ApiResponse sendHtmlMail(top.zby.model.Mail mail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);
            javaMailSender.send(message);
            return ApiResponse.success("邮件发送成功");
        } catch (MessagingException e) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, "邮件发送失败: " + e.getMessage());
        }
    }

    @Override
    public ApiResponse sendAttachmentsMail(Mail mail, MultipartFile[] files) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);

            if (files != null) {
                for (MultipartFile file : files) {
                    // 确保文件不为null且不为空
                    if (file != null && !file.isEmpty()) {
                        // MimeMessageHelper的addAttachment会抛出MessagingException，由外层统一捕获
                        helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), new ByteArrayResource(file.getBytes()));
                    }
                }
            }
            javaMailSender.send(message);
        } catch (Exception e) {
            return ApiResponse.fail(HttpStatus.BAD_REQUEST, "邮件发送失败: " + e.getMessage());
        }
        return ApiResponse.success("邮件发送成功");
    }
}
