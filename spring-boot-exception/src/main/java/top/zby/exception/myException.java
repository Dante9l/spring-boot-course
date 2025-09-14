package top.zby.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import top.zby.enums.ErrorCode;

@Getter
@EqualsAndHashCode(callSuper = true)
public class myException extends RuntimeException{
    private int code;
    private String msg;

    public myException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public myException(String msg) {
        this.code = ErrorCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public myException(String msg, Throwable e) {
        this.code = ErrorCode.SERVER_ERROR.getCode();
        this.msg = msg;
    }
}
