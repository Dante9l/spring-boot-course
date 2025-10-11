package top.zby.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ASUS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private String phone;

    private String msg;

    public LoginResponse(String token, String phone) {
        this.token=token;this.phone=phone;this.msg="登录成功";
    }
}
