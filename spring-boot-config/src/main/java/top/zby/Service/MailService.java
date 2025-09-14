package top.zby.Service;

import org.springframework.web.multipart.MultipartFile;
import top.zby.common.ApiResponse;
import top.zby.model.Mail;

public interface MailService {
    ApiResponse send(Mail mail);
    ApiResponse sendHtmlMail(Mail mail);
    ApiResponse sendAttachmentsMail(Mail mail, MultipartFile[] file);
}
