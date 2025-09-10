package top.zby.Service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zby.Service.OssService;
import top.zby.config.OssConfig;

import java.io.IOException;
import java.io.InputStream;

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
            //创建客户端
            OSS oss = new OSSClientBuilder().build(endpoint, accessKey, secretKey);

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(file.getContentType());
            meta.setContentDisposition("inline");
            String uploadUrl = dir + newFileName;
            InputStream stream;
            try {
                stream = file.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            oss.putObject(bucket, uploadUrl, stream, meta);
            oss.shutdown();

            return "https://" + bucket + "." + endpoint + "/" + uploadUrl;
        }
        return "上传失败";
    }
}