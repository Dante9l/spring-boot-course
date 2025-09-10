package top.zby.common;

import lombok.Builder;
import lombok.Data;
import top.zby.enums.ResultCode;

/**
 * @author ASUS
 */
@Data
@Builder
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;
    
    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(String msg, T data) {
        return new ApiResponse<>(ResultCode.SUCCESS.getCode(), msg, data);
    }
    public static <T> ApiResponse<T> success(String msg) {
        return new ApiResponse<>(ResultCode.SUCCESS.getCode(), msg , null);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResultCode.SUCCESS.getCode(), "success", null);
    }

    public static <T> ApiResponse<T> fail(ResultCode resultCode ,String msg) {
        return new ApiResponse<>(400, msg, null);
    }

}
