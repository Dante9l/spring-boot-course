package top.zby.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ASUS
 */
@RestController
public class MoodController {

    @Value("${my.mood.today}")
    private String today;

    @Value("${my.mood.content}")
    private String content;

    @Value("${my.mood.name}")
    private String name;

    @GetMapping("/mood")
    public String getMood() {
        return "今天是" + today + "，" + name + "，" + content;
    }
}
