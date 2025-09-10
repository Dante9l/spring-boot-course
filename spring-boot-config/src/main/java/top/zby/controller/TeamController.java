package top.zby.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.model.Team;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {

    @PostMapping("/create")
    public String createTeam(@Valid @RequestBody Team team) {
        log.info("创建团队:" +  team);
        return "创建团队成功";
    }
}
