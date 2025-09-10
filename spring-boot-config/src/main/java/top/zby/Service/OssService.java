package top.zby.Service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String upload(MultipartFile file);
}
