package top.zby.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.zby.Service.OssService;
import top.zby.common.ApiResponse;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    private OssService ossService;

    @RequestMapping("/upload")
    public ResponseEntity<ApiResponse<String>> upload(MultipartFile file)
    {
        String upload = ossService.upload(file);
        if (upload.equals("上传失败"))
            return ResponseEntity.ok(ApiResponse.fail(HttpStatus.BAD_REQUEST, upload));
        return ResponseEntity.ok(ApiResponse.success("上传成功",upload));
    }
}
