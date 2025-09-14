package top.zby.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.zby.common.Result;
import top.zby.exception.myException;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(myException.class)
    public Result<String> handlemyException(myException e) {
        return Result.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
