package top.zby.Service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zby.Service.OssService;
import top.zby.config.OssConfig;

import java.io.IOException;

@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Resource
    private OssConfig ossConfig;
    @Override
    public String upload(MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            //获取后缀名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + suffix;
            log.info("上传文件名：{}", suffix);
            //读取配置
            String endpoint = ossConfig.getEndpoint();
            String accessKey = ossConfig.getAccessKey();
            String secretKey = ossConfig.getSecretKey();
            String bucket = ossConfig.getBucket();
            String dir = ossConfig.getDir();
            //上传
            OSS oss = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
            String uploadUrl = dir + newFileName;
            try {
                oss.putObject(bucket, uploadUrl, file.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            oss.shutdown();

            return "https://" + bucket + "." + endpoint + "/" + uploadUrl;
        }
        return null;
    }
}
