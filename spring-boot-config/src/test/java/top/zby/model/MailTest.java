package top.zby.model;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zby.Service.MailService;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * MailServiceImpl 单元测试类
 */
@SpringBootTest
class MailServiceImplTest {

    @Resource
    private MailService mailService;
    @Test
    void testSend_NormalMail_ReturnsSuccessMessage() {
        // 准备测试数据
        Mail mail = new Mail();
        mail.setTo("3487411869@qq.com");
        mail.setSubject("Test Subject");
        mail.setContent("Test Content");
        mailService.send(mail);

    }

    /**
     * 测试传入 null 参数的情况
     */
    @Test
    void testSend_NullMail_ThrowsNullPointerException() {
        // 验证抛出空指针异常
        assertThrows(NullPointerException.class, () -> {
            mailService.send(null);
        });
    }

    /**
     * 测试 Mail 对象字段为空的情况
     */
    @Test
    void testSend_MailWithEmptyFields_CallsSend() {
        Mail mail = new Mail();
        mail.setTo("3487411869@qq.com"); // 空字符串
        mail.setSubject(null); // null 值
        mail.setContent(null); // null 值

        mailService.send(mail);
    }
}
