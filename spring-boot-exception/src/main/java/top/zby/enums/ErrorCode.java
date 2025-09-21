package top.zby.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    BAD_REQUEST(400, "参数错误"),

    NOT_FOUND(404, "未找到该资源"),

    UPDATE_FAIL(500,"更新失败,请稍后再试"),

    UNAUTHORIZED(401, "登录失效"),

    NOT_PERMISSION(403, "权限不足"),

    METHOD_ERROR(405, "方法不允许"),

    SERVER_ERROR(500, "服务器内部错误");

    int code;
    String msg;

    ErrorCode(int code, String message) {
        this.code = code;
        this.msg = message;
    }
}
