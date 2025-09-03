package top.zby.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
        /**
     * 处理GET请求，返回"Hello World!"字符串
     *
     * @return 返回问候语"Hello World!"
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
