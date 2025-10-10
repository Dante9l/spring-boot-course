package top.zby.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.Service.LoginService;
import top.zby.common.Result;
import top.zby.entity.LoginRequest;
import top.zby.entity.LoginResponse;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Resource
    LoginService loginService;
    @PostMapping
    public Result<LoginResponse> login(@RequestBody LoginRequest  request) {
        LoginResponse login = loginService.login(request);
        return Result.ok(login);
    }

}
