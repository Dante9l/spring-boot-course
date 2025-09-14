package top.zby.common;

import lombok.Builder;
import lombok.Data;

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

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(200, msg, data);
    }
    public static <T> ApiResponse<T> success(String msg) {
        return new ApiResponse<>(200, msg , null);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "success", null);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<>(400, msg, null);
    }

}
