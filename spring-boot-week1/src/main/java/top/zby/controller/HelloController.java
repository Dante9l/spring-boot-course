package top.zby.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${my.feature.enabled}")
    private Boolean isAvailable;

        /**
     * 处理GET请求，返回"Hello World!"字符串
     *
     * @return 返回问候语"Hello World!"
     */
    @GetMapping("/hello")
    public String hello() {
        if (isAvailable){
            return "Hello World!";
        }else {
            return "That unavailable!";
        }
    }

}
