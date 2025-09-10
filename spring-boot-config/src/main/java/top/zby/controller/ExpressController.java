package top.zby.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.enums.ExpressStatus;

@RestController
@RequestMapping("/express")
@Slf4j
public class ExpressController {
    @GetMapping("/{status}")
    public String express(@PathVariable ExpressStatus status) {
        return "当前快递状态:" + status.getLabel();
    }
}
