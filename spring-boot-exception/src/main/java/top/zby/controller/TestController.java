package top.zby.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.zby.common.Result;
import top.zby.entity.User;
import top.zby.service.ExceptionService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private ExceptionService exceptionService;

    @GetMapping("/{id}")
    public Result<String> test(@PathVariable("id") int id)
    {
        if (id == 1)
        {
            exceptionService.unAuthorized();
        }else if (id == 2) {
            exceptionService.notPermission();
        }else if (id == 3) {
            id = id / 0;
            exceptionService.notFound();
        }
        return Result.ok("查询成功");
    }

    @PostMapping("/user")
    public Result<?> addUser(@Valid @RequestBody User user, BindingResult bindResult)
    {
        if (bindResult.hasErrors()){
            return Result.error(bindResult.getAllErrors().get(0).getDefaultMessage());
        }
        return Result.ok(user);
    }
}
