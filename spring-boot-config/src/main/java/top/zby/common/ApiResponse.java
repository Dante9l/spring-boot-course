package top.zby.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author ASUS
 */
@Data
@Builder
public class ApiResponse<T> {
    private HttpStatus code;
    private String msg;
    private T data;
    
    public ApiResponse(HttpStatus code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(HttpStatus.OK, msg, data);
    }
    public static <T> ApiResponse<T> success(String msg) {
        return new ApiResponse<>(HttpStatus.OK, msg , null);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(HttpStatus.OK, "success", null);
    }

    public static <T> ApiResponse<T> fail(HttpStatus status, String msg) {
        return new ApiResponse<>( status, msg, null);
    }

}
