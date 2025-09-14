package top.zby.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

/**
 * @author ASUS
 */
@Slf4j
@RestController
public class QrCodeController {

    @Value("${custom.qrcode.content}")
    private String url;

    @GetMapping("/qrCode")
    public ResponseEntity<byte[]> qrCode() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        QrCodeUtil.generate("HelloWorld", 300, 300, ImgUtil.IMAGE_TYPE_PNG, out);
        byte[] bytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        log.info("生成二维码成功");

        return ResponseEntity.ok()
                .headers(headers)
                .body(bytes);
    }
}
