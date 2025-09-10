package top.zby.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.enums.Drink;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/drink")
@Slf4j
public class DrinkController {
    @GetMapping("/{type}")
    public String sell(@PathVariable Drink type) {

        return null;
    }
}

