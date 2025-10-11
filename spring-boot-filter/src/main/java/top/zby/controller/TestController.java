package top.zby.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test() {
        log.info("进入controller");
        return "hello world";
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable String id) {
        log.info("进入pay,id {}", id);
        return "pay success";
    }

    @GetMapping("/order")
    public String order(@RequestParam String user) {
        log.info("进入user:{}订单生成环节",  user);
        return "order success";
    }
}
