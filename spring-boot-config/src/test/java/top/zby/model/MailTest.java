package top.zby.model;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import top.zby.Service.MailService;

import java.io.File;

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

    @Test
    void testSend_HtmlMail_ReturnsSuccessMessage() {
        // 创建 HTML 邮件对象
        Mail mail = new Mail();
        mail.setTo("3487411869@qq.com");
        mail.setSubject("Test Subject");
        String htmlContent = """
                <!DOCTYPE html>
                <html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
                <head>
                  <meta charset="utf-8">
                  <meta name="x-apple-disable-message-reformatting">
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <meta name="color-scheme" content="light dark">
                  <meta name="supported-color-schemes" content="light dark">
                  <title>邮件标题</title>
                  <!--[if mso]>
                  <style type="text/css">
                    body, table, td, a { font-family: Arial, Helvetica, sans-serif !important; }
                  </style>
                  <![endif]-->
                  <style>
                    /* 移动端适配 */
                    @media screen and (max-width: 600px) {
                      .container { width: 100% !important; }
                      .stack { display: block !important; width: 100% !important; }
                      .p-24 { padding: 16px !important; }
                      .hero-img { height: auto !important; }
                      .cta { display: block !important; width: 100% !important; }
                    }
                    /* 深色模式（受支持客户端） */
                    @media (prefers-color-scheme: dark) {
                      .bg { background-color: #0e1116 !important; }
                      .card { background-color: #151a22 !important; }
                      .text { color: #e6e6e6 !important; }
                      .muted { color: #9aa4b2 !important; }
                      .divider { border-color: #2a3240 !important; }
                      .btn { background-color: #4f8cff !important; color: #ffffff !important; }
                      a { color: #86b7ff !important; }
                      .logo-text { color: #ffffff !important; }
                    }
                  </style>
                </head>
                                
                <body style="margin:0; padding:0; background:#f3f5f8;">
                  <!-- 预览文案（收件箱列表中显示） -->
                  <div style="display:none; max-height:0; overflow:hidden; mso-hide:all;">
                    这里是预览文案：一句话总结邮件内容，约 80-100 个字符。
                  </div>
                                
                  <!-- 整体容器 -->
                  <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" class="bg" style="background:#f3f5f8;">
                    <tr>
                      <td align="center" style="padding:24px;">
                        <table role="presentation" width="600" cellpadding="0" cellspacing="0" border="0" class="container" style="width:600px; max-width:100%;">
                         \s
                          <!-- 顶部品牌栏 -->
                          <tr>
                            <td align="left" style="padding:12px 8px;">
                              <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                  <td style="font:700 18px/1.2 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;" class="logo-text">
                                    <!-- 可替换为 <img src="https://..." alt="Brand" width="120" style="border:0; display:block;"> -->
                                    品牌名称
                                  </td>
                                  <td align="right" style="font:400 12px/1.2 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#6b7280;">
                                    <a href="%%webversion%%" style="color:#6b7280; text-decoration:underline;">在浏览器中查看</a>
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                                
                          <!-- 卡片主体 -->
                          <tr>
                            <td class="card" style="background:#ffffff; border-radius:12px; overflow:hidden;">
                              <!-- Hero 图 -->
                              <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                  <td>
                                    <img class="hero-img" src="https://images.unsplash.com/photo-1529336953121-adfd2f3f3f66?q=80&w=1200&auto=format&fit=crop" width="600" alt="Hero" style="display:block; width:100%; height:auto; border:0;">
                                  </td>
                                </tr>
                              </table>
                                
                              <!-- 标题与正文 -->
                              <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                  <td class="p-24" style="padding:24px;">
                                    <h1 class="text" style="margin:0 0 8px; font:700 24px/1.3 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;">
                                      欢迎加入我们的最新活动 🎉
                                    </h1>
                                    <p class="text" style="margin:0 0 16px; font:400 15px/1.7 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#374151;">
                                      这是简短的开场说明，向读者说明这封邮件的价值与主要动作。你可以在此放入简明的优惠、更新或公告摘要。
                                    </p>
                                
                                    <!-- CTA 按钮 -->
                                    <table role="presentation" cellpadding="0" cellspacing="0" border="0" style="margin:0 0 20px;">
                                      <tr>
                                        <td>
                                          <!--[if mso]>
                                          <v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" href="https://example.com" arcsize="8%" strokecolor="#4f46e5" fillcolor="#4f46e5" style="height:44px;v-text-anchor:middle;width:220px;">
                                            <w:anchorlock/>
                                            <center style="color:#ffffff; font-family:Arial, sans-serif; font-size:16px; font-weight:bold;">
                                              立即行动
                                            </center>
                                          </v:roundrect>
                                          <![endif]-->
                                          <![if !mso]><a class="btn cta" href="https://example.com" target="_blank"
                                            style="display:inline-block; background:#4f46e5; color:#ffffff; text-decoration:none; font:700 16px/44px -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; padding:0 20px; border-radius:8px; min-width:200px; text-align:center;">
                                            立即行动
                                          </a><![endif]>
                                        </td>
                                      </tr>
                                    </table>
                                
                                    <!-- 特色要点 -->
                                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" style="margin:0 0 8px;">
                                      <tr>
                                        <td style="font:400 14px/1.8 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;" class="text">
                                          ✅ 要点一：一句话说明优势或新功能
                                        </td>
                                      </tr>
                                      <tr>
                                        <td style="font:400 14px/1.8 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;" class="text">
                                          ✅ 要点二：强调价值或节省成本/时间
                                        </td>
                                      </tr>
                                      <tr>
                                        <td style="font:400 14px/1.8 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;" class="text">
                                          ✅ 要点三：制造紧迫感或社会证明
                                        </td>
                                      </tr>
                                    </table>
                                
                                    <!-- 分割线 -->
                                    <hr class="divider" style="border:none; border-top:1px solid #e5e7eb; margin:20px 0;">
                                
                                    <!-- 两栏内容 -->
                                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                                      <tr>
                                        <td class="stack" width="50%" valign="top" style="padding-right:8px;">
                                          <h3 style="margin:0 0 6px; font:700 16px/1.4 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;" class="text">
                                            常见问题
                                          </h3>
                                          <p style="margin:0 0 12px; font:400 14px/1.7 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#374151;" class="text">
                                            Q：我该如何开始？<br>
                                            A：点击上方按钮，按照引导完成设置。
                                          </p>
                                        </td>
                                        <td class="stack" width="50%" valign="top" style="padding-left:8px;">
                                          <h3 style="margin:0 0 6px; font:700 16px/1.4 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#111827;" class="text">
                                            客户评价
                                          </h3>
                                          <p style="margin:0 0 12px; font:400 14px/1.7 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#374151;" class="text">
                                            “这项服务帮助我们把上线时间缩短了 50%！”— 某企业用户
                                          </p>
                                        </td>
                                      </tr>
                                    </table>
                                
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                                
                          <!-- 页脚 -->
                          <tr>
                            <td style="padding:16px 8px;">
                              <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                  <td align="left" class="muted" style="font:400 12px/1.6 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#6b7280;">
                                    <div>
                                      <strong>品牌名称</strong> · 公司地址第一行 · 城市/省份/邮编
                                    </div>
                                    <div style="margin-top:6px;">
                                      如不想再收到此类邮件，可在此
                                      <a href="%%unsubscribe%%" style="color:#6b7280; text-decoration:underline;">退订</a>。
                                      如误收此邮件，请忽略。
                                    </div>
                                    <div style="margin-top:6px;">
                                      联系我们：<a href="mailto:support@example.com" style="color:#6b7280; text-decoration:underline;">support@example.com</a>
                                    </div>
                                  </td>
                                  <td align="right" class="muted" style="font:400 12px/1.6 -apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,'Hiragino Sans GB','Microsoft YaHei',sans-serif; color:#6b7280;">
                                    © 2025 Brand Inc.
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                                
                        </table>
                      </td>
                    </tr>
                  </table>
                                
                  <!-- 纯文本版本（针对纯文本客户端，可选）：使用发送平台提供的多部件邮件功能 -->
                  <!--
                  欢迎加入我们的最新活动
                  这里是预览文案……
                  - 要点一
                  - 要点二
                  - 要点三
                  立即行动：https://example.com
                  退订：https://example.com/unsub
                  -->
                </body>
                </html>
                                
                """;
        mail.setContent(htmlContent);
        mailService.sendHtmlMail(mail);
    }


    /**
     * 测试传入 null 参数的情况
     */
    @Test
    void testSend_MutiMail_ReturnsSuccessMessage() throws  Exception {
        // 创建 HTML 邮件对象
        Mail mail = new Mail();
        mail.setTo("3487411869@qq.com");
        mail.setSubject("Test Subject");
        mail.setContent("test");
        File file = new File("C:/Users/ASUS/Pictures/Saved Pictures/weqwe.jpg");
        File file1 = new File("C:/Users/ASUS/Pictures/Saved Pictures/awd.jpg");
        MultipartFile[] files = new MultipartFile[2];
        files[0] = new MockMultipartFile("file", file.getName(), "image/jpeg", FileCopyUtils.copyToByteArray(file));
        files[1] = new MockMultipartFile("file1", file1.getName(), "image/jpeg", FileCopyUtils.copyToByteArray(file1));
        mailService.sendAttachmentsMail(mail, files);
    }
    /**
     * 测试 Mail 对象字段为空的情况
     */

}
