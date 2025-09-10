package top.zby.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun-oss")
@Data
public class OssConfig {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String dir;

}
